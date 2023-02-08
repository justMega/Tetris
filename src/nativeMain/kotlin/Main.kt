
import kaylib.kCore.*
import kaylib.kEnums.ConfigFlag
import kaylib.kMath.kVector2
import kaylib.kShapes.drawLine
import kaylib.kShapes.kRectangle
import kaylib.kText.drawText
import kaylib.kText.getFontDefault
import kaylib.kText.measureText
import kaylib.kTypes.kColor
import kaylibc.*
import kotlin.random.Random

//import java.nio.file.Path

//------------------------------------------------------------------------------------
// Program main entry point
//------------------------------------------------------------------------------------
fun main() {
    setConfigFlags(ConfigFlag.VSYNC_HINT) // Turn VSYNC On
    initWindow(MySingleton.SCREEN_WIDTH + 300, MySingleton.SCREEN_HEIGHT, "Tetris")

    //val path = getWorkingDirectory()
    val bord = Bord()
    val tetrominoList = arrayListOf<Tetromino>()
    // Main game loop
    while (!windowShouldClose) { // Detect window close button or ESC key
        whileWindowIsOpen {
            if (MySingleton.gameState == MySingleton.GameState.RUNNING) {
                bord.canDelete()
                tetrominoList[0].update()
                if (tetrominoList.size == 1) {
                    Tetromino.spawn(tetrominoList, bord)
                }
            }

            // Draw
            clearBackground(rayWhite)
            if (MySingleton.gameState == MySingleton.GameState.RUNNING) {
                tetrominoList[0].draw()
                tetrominoList[1].draw(kVector2(MySingleton.SCREEN_WIDTH + 20f, 100f))
                bord.draw()
                drawText("SCORE: ${MySingleton.score}", MySingleton.SCREEN_WIDTH + 10, 10, 40, black)
                bord.hasLost()
            }
            if (MySingleton.gameState == MySingleton.GameState.GAME_OVER){
                val fontSize = 60
                val text = "GAME OVER"
                val textSize = measureText(getFontDefault(), text, fontSize.toFloat(), 1f)
                val halfX = (MySingleton.SCREEN_WIDTH + 300)/ 2
                val halfY = MySingleton.SCREEN_HEIGHT / 2
                drawText(text, halfX - textSize.x.toInt() / 2, halfY - textSize.y.toInt()/ 2, fontSize, black)
                val restartButton = Button(kRectangle(halfX.toFloat(), halfY + textSize.y + 10f, 150f, 50f), "restart")
                restartButton.draw()
                if(restartButton.isPressed()){
                    MySingleton.score = 0
                    bord.reset()
                    tetrominoList.clear()
                    Tetromino.spawn(tetrominoList, bord)
                }
            }
            if (MySingleton.gameState == MySingleton.GameState.START){
                val fontSize = 60
                val text = "Tetris"
                val textSize = measureText(getFontDefault(), text, fontSize.toFloat(), 1f)
                val halfX = (MySingleton.SCREEN_WIDTH + 300)/ 2
                val halfY = MySingleton.SCREEN_HEIGHT / 2
                drawText(text, halfX - textSize.x.toInt() / 2, halfY - textSize.y.toInt()/ 2, fontSize, black)
                val startButton = Button(kRectangle(halfX.toFloat(), halfY + textSize.y + 10f, 150f, 50f), "start")
                startButton.draw()
                if(startButton.isPressed()){
                    MySingleton.score = 0
                    bord.reset()
                    tetrominoList.clear()
                    Tetromino.spawn(tetrominoList, bord)
                }
            }
        }
    }
    // De-Initialization
    closeWindow()
}