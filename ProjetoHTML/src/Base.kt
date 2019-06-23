import kotlin.browser.window

fun main(){
    println("Olá Mundo")
}

fun apertar(){
    window.alert("Este é um pop-up legal")
}

abstract class PositionList<out T>
data class Position<T> (val info:Int, val prox: PositionList<T>) : PositionList<T>()
object Null:PositionList<Nothing>()

fun generateBoard():Array<IntArray>
{
    return arrayOf(intArrayOf(-1,-1,1,1,1,-1,-1),
                        intArrayOf(-1,-1,1,1,1,-1,-1),
                        intArrayOf( 1, 1,1,1,1, 1, 1),
                        intArrayOf( 1, 1,1,0,1, 1, 1),
                        intArrayOf( 1, 1,1,1,1, 1, 1),
                        intArrayOf(-1,-1,1,1,1,-1,-1),
                        intArrayOf(-1,-1,1,1,1,-1,-1))
}

fun <T> generateBoard(size:Int):Array<PositionList> = {
    return arrayOf(generateLine(0,0, size),
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