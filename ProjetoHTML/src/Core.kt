
abstract class PositionList<out T>
data class Position<T> (val info:Int, val prox: PositionList<T>) : PositionList<T>()
object Null:PositionList<Nothing>()


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

fun <T> generateBoard(size:Int):Array<PositionList> = {
    return arrayOf(
            generateLine(0,0, size),
            generateLine(1,0, size),
            generateLine(2,0, size),
            generateLine(3,0, size),
            generateLine(4,0, size),
            generateLine(5,0, size),
            generateLine(6,0, size),
            generateLine(7,0, size)
    )
}

fun <T> generateLine(currentLine:Int, position:Int, size:Int):PositionList<T> {
    return when {
        currentLine < size -> {
            if (position < 2 || position > 5) {
                if(currentLine < 2 || currentLine > 5) {
                    Position(-1, generateLine<T>(currentLine, position + 1, size))
                } else {
                    Position(1, generateLine<T>(currentLine, position + 1, size))
                }
            } else if (position == floor(size/2) && currentLine == floor(size/2)) {
                Position(0, generateLine<T>(currentLine, position + 1, size))
            } else{
                Position(1, generateLine<T>(currentLine, position + 1, size))
            }
        }
        else -> Null
    }
}

fun isValidMove(board:Array<PositionList>, x:Int, y:Int, xDest:Int, yDest:Int):Boolean = {
    var ret = false

    if(xDest >= 0 && xDest < board.size() && yDest >= 0 && yDest < board.size()) {
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

fun isPositionValid(board:Array<PositionList>, x:Int, y:Int):Boolean {
    return board[x][y].info != -1
}

fun move(x:Int, y:Int){

    if (isPositionValid(tabuleiro,x,y)){

    }
}

