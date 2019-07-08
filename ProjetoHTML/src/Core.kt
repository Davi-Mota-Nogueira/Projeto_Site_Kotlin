import org.w3c.dom.*
import kotlin.browser.*

abstract class PositionList<out Int>
data class Position<Int> (val info:Int, val prox: PositionList<Int>) : PositionList<Int>()
object Null:PositionList<Nothing>()

fun getElementFromDocument(name:String):HTMLElement {
    val e = document.getElementById(name)
    if (e==null) {
        println("Careful, the element $name was not found.")
    }
    return e as HTMLElement
}

val table = getElementFromDocument("tabuleiro") as HTMLTableElement

// Initialize as empty board
var board = emptyArray<Array<Int>>()

/**
 * Generate a single line, according to the Solitaire rule.
 *
 * X X 1 1 1 X X
 * X X 1 1 1 X X
 * 1 1 1 1 1 1 1
 * 1 1 1 0 1 1 1
 * 1 1 1 1 1 1 1
 * X X 1 1 1 X X
 * X X 1 1 1 X X
 *
 * 1 - Filled place / 0 - Empty place / -1/X - Invalid place
 *
 */
@JsName("generateBoard")
fun generateBoard() {
    println("Initializing a new Board")
    board = arrayOf(
            Array<Int>(7, { n -> if (n < 2 || n > 5) -1 else 1}),
            Array<Int>(7, { n -> if (n < 2 || n > 5) -1 else 1}),
            Array<Int>(7, {1}),
            Array<Int>(7, { n -> if (n == 3) 0 else 1}),
            Array<Int>(7, {1}),
            Array<Int>(7, { n -> if (n < 2 || n > 5) -1 else 1}),
            Array<Int>(7, { n -> if (n < 2 || n > 5) -1 else 1})
    )
}

/**
 * Test if the winning condition was achieved.
 * Goes testing line by line.
 *
 * @param board current board
 */
fun winner(board:Array<Array<Int>>):Int{
    fun aux(board:Array<Array<Int>>, cont:Int, i:Int):Int = when{
        board[i].filter({x:Int-> x == 1}).isNotEmpty() ->
            aux(board, cont + 1, i + 1)
            board[i].filter({ x: Int -> x == 1 }).isEmpty() -> aux(board, cont, i+1)
        else -> cont
    }
    return aux(board,0,0)
}
/**
 * Test if a single move is valid
 *
 * @param tabuleiro position map
 * @param x current column
 * @param y current row
 * @param xDest destination column
 * @param yDest destination row
 * @return moviment validation 2 steps for each side
 */
fun isValidMove(x:Int, y:Int, xDest:Int, yDest:Int):Boolean {
    var ret:Boolean = false

    if(xDest >= 0 && xDest < board.size && yDest >= 0 && yDest < board.size) {

        var currentPos = Pair(x,y)
        var destPos = Pair(xDest,yDest)
        var interPos = getIntermediatePosition(currentPos, destPos)

        if (isPositionEmpty(destPos.first, destPos.second)) {
            if(board[interPos.first][interPos.second] == 1)
            {
                ret = true
            }
        }
    }

    return ret
}

fun isPositionEmpty(x:Int, y:Int):Boolean {
    return board[x][y] == 0
}

/**
 * Test if the position isn't invalid (out of the board)
 *
 * @param board current board
 * @param x current row
 * @param y current line
 */
fun isPositionValid(x: Int,y: Int):Boolean{
    return board[x][y] != -1
}
/**
 * Encapsulate the vertical move test
 *
 * @param tabuleiro position map
 * @param x current column
 * @param y current row
 * @return moviment validation 2 steps for each side
 */
fun isValidVerticalMove(x:Int,y:Int):Boolean
{
    return isValidMove(x,y,x,y + 1) && isValidMove(x,y,x,y + 2)
            && isValidMove(x,y,x,y - 1) && isValidMove(x,y,x,y - 2)
}

/**
 * Encapsulate the horizontal move test
 *
 * @param tabuleiro position map
 * @param x current column
 * @param y current row
 * @return moviment validation 2 steps for each side
 */
fun isValidHorizontalMove(x:Int,y:Int):Boolean
{
    return isValidMove(x,y,x - 2,y) && isValidMove(x,y,x - 1,y)
            && isValidMove(x,y,x + 1,y) && isValidMove(x,y,x + 2,y)
}

fun getHorizontalMoves(x:Int, y:Int):List<Pair<Int,Int>>{
    var positions:List<Pair<Int,Int>> = mutableListOf()

    if(isValidMove(x,y,x - 2, y)){
        positions += Pair(x - 2,y)
    }

    if(isValidMove(x,y,x + 2, y)){
        positions += Pair(x + 2,y)
    }

    return positions
}

fun getVerticalMoves(x:Int, y:Int):List<Pair<Int,Int>>{
    var positions:List<Pair<Int,Int>> = mutableListOf()

    if(isValidMove(x,y,x, y - 2)){
        positions += Pair(x,y - 2)
    }

    if(isValidMove(x,y,x, y + 2)){
        positions += Pair(x,y + 2)
    }

    return positions
}

fun changeImage(position:Pair<Int,Int>, filled:Boolean) {
    var currentId = position.first.toString() + position.second.toString()
    var currentCell = getElementFromDocument(currentId)

    if(filled)
        currentCell.innerHTML = "<a href=\"#\" onclick=\"Projeto_Site_Kotlin.move(" + position.first.toString() + "," + position.second.toString() + " )\"><img src=\"img/bola.png\"></a>"
    else
        currentCell.innerHTML = "<a href=\"#\" onclick=\"Projeto_Site_Kotlin.move(" + position.first.toString() + "," + position.second.toString() + " )\"><img src=\"img/sem-bola.png\"></a>"

}

fun getIntermediatePosition(src:Pair<Int,Int>, dest:Pair<Int,Int>):Pair<Int,Int> {
    var x:Int = src.first
    var y:Int = src.second

    if(src.first == dest.first)
    {
        if(src.second > dest.second)
            y = src.second - 1
        else
            y = src.second + 1
    }
    else
    {
        if(src.first > dest.first)
            x = src.first - 1
        else
            x = src.first + 1
    }

    return Pair(x,y)
}

fun makeMove(src:Pair<Int,Int>, dest:Pair<Int,Int>)
{
    changeImage(src, false)
    board[src.first][src.second] = 0

    val inter = getIntermediatePosition(src, dest)
    changeImage(inter, false)
    board[inter.first][inter.second] = 0

    changeImage(dest, true)
    board[dest.first][dest.second] = 1
}

@JsName("move")
fun move(x:Int, y:Int){
    if (isPositionValid(x,y) && !isPositionEmpty(x,y)) {

        // Pega as possibilidades de movimento
        val hMoves: List<Pair<Int, Int>> = getHorizontalMoves(x, y)
        val vMoves: List<Pair<Int, Int>> = getVerticalMoves(x, y)

        if (hMoves.isNotEmpty() || vMoves.isNotEmpty()) {

            print("Horizontal Possitilities: ")
            println(hMoves)
            print("Vertical Possitilities: ")
            println(vMoves)

            if(hMoves.isNotEmpty() && vMoves.isNotEmpty()) {
                println("Two Possibilities")

                if(hMoves.size >= hMoves.size)
                {
                    makeMove(Pair(x,y),hMoves.get(0))
                }
                else
                {
                    makeMove(Pair(x,y),vMoves.get(0))
                }
            }
            else{
                if(hMoves.isNotEmpty())
                {
                    makeMove(Pair(x,y),hMoves.get(0))
                }
                else
                {
                    makeMove(Pair(x,y),vMoves.get(0))
                }
            }
        }

        // Condição de vitória
        if(winner(board) == 1)
        {
            println("Parabéns, você ganhou!")
        }
    }
}

