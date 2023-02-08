import kaylib.kMath.kVector2
import kaylib.kShapes.drawLine
import kaylib.kShapes.drawRectangle
import kaylibc.black
import kaylibc.white

class Bord {
    private val indexX = MySingleton.SCREEN_WIDTH / MySingleton.SIZE
    private val indexY = MySingleton.SCREEN_HEIGHT / MySingleton.SIZE
    private var bord = Array(indexY) {Array(indexX) {false} }
    private var bordColor = Array(indexY) {Array(indexX) {white} }
    private val pos = kVector2(0f,0f)

    val getBord
        get() = bord
    val getBordColor
        get() = bordColor

    fun draw(){
        val vmPos = kVector2(pos.x, pos.y)
        for(i in bord.indices){
            //println("hello $i")
            for (j in bord[i].indices){
                //println("$i, $j")
                //println("${vmPos.x}, ${vmPos.y}")
                if (bord[i][j]) {
                    drawRectangle(vmPos, kVector2(MySingleton.SIZE.toFloat(), MySingleton.SIZE.toFloat()), bordColor[i][j])
                }
                vmPos.x += MySingleton.SIZE
            }
            vmPos.y += MySingleton.SIZE
            vmPos.x = pos.x
        }
        for(i in 1..10){
            drawLine(MySingleton.SIZE * i, 0, MySingleton.SIZE * i, MySingleton.SCREEN_HEIGHT, black)
        }
        val sizeX = MySingleton.SIZE / 10
        val sizeY = MySingleton.SCREEN_HEIGHT / 10
        var startPosY = sizeX * MySingleton.SCREEN_HEIGHT / sizeY
        while(startPosY < MySingleton.SCREEN_HEIGHT){
            drawLine(0, startPosY, MySingleton.SCREEN_WIDTH, startPosY, black)
            startPosY += sizeX * MySingleton.SCREEN_HEIGHT / sizeY
        }
    }

    fun canDelete(){
        for(i in bord.indices){
            var jeVrsta = true
            for (j in bord[i].indices){
                if (!bord[i][j]) {
                    jeVrsta = false
                    break
                }
            }
            if (jeVrsta){
                MySingleton.score += 10
                //println("start")
                moveDown(i)
                for (j in bord[0].indices){
                    bord[0][j] = false
                    bordColor[0][j] = white
                }
            }
        }
    }

    private fun moveDown(od: Int){
        for (i in od downTo 1){
            //println("$i ${i - 1}")
            bord[i] = bord[i - 1].copyOf()
            bordColor[i] = bordColor[i - 1].copyOf()
        }
    }

    fun hasLost(){
        if (bord[0][0] || bord[0][1] || bord[0][2] || bord[0][3]){
            MySingleton.gameState = MySingleton.GameState.GAME_OVER
        }
    }
    fun reset(){
        bord = Array(indexY) {Array(indexX) {false} }
        bordColor = Array(indexY) {Array(indexX) {white} }
        MySingleton.gameState = MySingleton.GameState.RUNNING
    }
}