import kaylib.kCore.getMousePosition
import kaylib.kCore.isMouseButtonPressed
import kaylib.kEnums.MouseButton
import kaylib.kShapes.checkCollision
import kaylib.kShapes.drawRectangle
import kaylib.kText.drawText
import kaylib.kText.getFontDefault
import kaylib.kText.measureText
import kaylibc.Rectangle
import kaylibc.black
import kaylibc.gray

class Button(private var rec: Rectangle, private val text: String) {
    init {
        this.rec.x -= rec.width / 2
    }

    fun draw(){
        drawRectangle(rec, gray)
        val fontSize = 30
        val textSize = measureText(getFontDefault(), text, fontSize.toFloat(), 1f)
        drawText(text, (rec.x + rec.width / 2 - textSize.x / 2).toInt(), (rec.y + rec.height / 2 - textSize.y / 2).toInt(), fontSize, black)
    }

    fun isPressed(): Boolean {
        return checkCollision(getMousePosition(), rec) && isMouseButtonPressed(MouseButton.LEFT)
    }

}