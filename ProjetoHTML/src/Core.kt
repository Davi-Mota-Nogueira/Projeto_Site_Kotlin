import org.w3c.dom.*
import kotlin.browser.*
import kotlin.math.*

abstract class PositionList<out T>
data class Position<T> (val info:Int, val prox: PositionList<T>) : PositionList<T>()
object Null:PositionList<Nothing>()

var tabuleiro:Array<PositionList> = emptyArray()

fun get(name:String):HTMLElement {
    val e = document.getElementById(name)
    if (e==null) {
        println("Careful, the element $name was not found.")
    }
    return e as HTMLElement
}

val table = get("tabuleiro") as HTMLTableElement
val img = get("bola") as HTMLImageElement

fun generateBoard():Array<IntArray>
{
    return arrayOf(
            intArrayOf(-1,-1,1,1,1,-1,-1),
            intArrayOf(-1,-1,1,1,1,-1,-1),
            intArrayOf( 1, 1,1,1,1, 1, 1),
            intArrayOf( 1, 1,1,0,1, 1, 1),
            intArrayOf( 1, 1,1,1,1, 1, 1),
            intArrayOf(-1,-1,1,1,1,-1,-1),
            intArrayOf(-1,-1,1,1,1,-1,-1)
    )
}

/**
 * Generate board by calling individual line generation
 *
 * @param size line size
 * @return 7x7 valid board
 */
fun <T> generateBoard(size:Int):Array<PositionList>
{
    return arrayOf(
            generateLine(0,0, size),
            generateLine(1,0, size),
            generateLine(2,0, size),
            generateLine(3,0, size),
            generateLine(4,0, size),
            generateLine(5,0, size),
            generateLine(6,0, size)
    )
}

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
 * @param currentLine Current Line
 * @param position Current column
 * @param size Line size
 */
fun <T> generateLine(currentLine:Int, position:Int, size:Int):PositionList<T> {
    return when {
        currentLine < size -> {
            if (position < 2 || position > 5) {
                if(currentLine < 2 || currentLine > 5) {
                    Position(-1, generateLine<T>(currentLine, position + 1, size))
                } else {
                    Position(1, generateLine<T>(currentLine, position + 1, size))
                }
            } else if (position == floor(size.toDouble()/2).toInt() && currentLine == floor(size.toDouble()/2).toInt()) {
                Position(0, generateLine<T>(currentLine, position + 1, size))
            } else{
                Position(1, generateLine<T>(currentLine, position + 1, size))
            }
        }
        else -> Null
    }
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
fun isValidMove(board:Array<PositionList>, x:Int, y:Int, xDest:Int, yDest:Int):Boolean {
    var ret:Boolean = false

    if(xDest >= 0 && xDest < board.size && yDest >= 0 && yDest < board.size) {
        if (isPositionEmpty(board, xDest, yDest)) {
            // movimento vertical
            if (xDest == x && (board[x][yDest - 1].info == 1 || board[x][yDest + 1].info == 1)) {
                ret = true
            }

            // movimento horizontal
            if (yDest == y && (board[xDest - 1][y].info == 1 || board[xDest + 1][y].info == 1)) {
                ret = true
            }
        }
    }

    return ret
}

fun isPositionEmpty(board:Array<PositionList>, x:Int, y:Int):Boolean {
    return board[x][y].info == 0
}

/**
 * Test if the position isn't invalid (out of the board)
 *
 * @param board current board
 * @param x current row
 * @param y current line
 */
fun isPositionValid(board:Array<PositionList>, x:Int, y:Int):Boolean {
    return board[x][y].info != -1
}

/**
 * Encapsulate the vertical move test
 *
 * @param tabuleiro position map
 * @param x current column
 * @param y current row
 * @return moviment validation 2 steps for each side
 */
fun isValidVerticalMove(tabuleiro:Array<PositionList>,x:Int,y:Int):Boolean
{
    return isValidMove(tabuleiro,x,y,x,y+1) && isValidMove(tabuleiro,x,y,x,y+2)
        && isValidMove(tabuleiro,x,y,x,y-1) && isValidMove(tabuleiro,x,y,x,y-2)
}

/**
 * Encapsulate the horizontal move test
 *
 * @param tabuleiro position map
 * @param x current column
 * @param y current row
 * @return moviment validation 2 steps for each side
 */
fun isValidHorizontalMove(tabuleiro:Array<PositionList>,x:Int,y:Int):Boolean
{
    return isValidMove(tabuleiro,x,y,x-2,y) && isValidMove(tabuleiro,x,y,x-1,y)
           && isValidMove(tabuleiro,x,y,x+1,y) && isValidMove(tabuleiro,x,y,x+2,y)
}

fun move(x:Int, y:Int){
    if (isPositionValid(tabuleiro,x,y)){
        if(isValidHorizontalMove(tabuleiro,x,y) && isValidVerticalMove(tabuleiro,x,y)){

            //https://kotlinlang.org/api/latest/jvm/stdlib/org.w3c.dom/-h-t-m-l-table-element/index.html
            val rows = table.rows[x].asList()

            //https://kotlinlang.org/api/latest/jvm/stdlib/org.w3c.dom/-h-t-m-l-collection/index.html
            println(rows.length)
            /*TODO, pegar o elemento por id e torcar as imagens e propriedades para onde ele vai, tipo de 0 para 1
            e de 1 para 0.
             */
        }
    }
}

