import kaylib.kCore.getFrameTime
import kaylib.kCore.isKeyPressed
import kaylib.kEnums.KeyboardKey
import kaylib.kMath.kVector2
import kaylib.kShapes.drawRectangle
import kaylib.kTypes.kColor
import kaylibc.Color
import kaylibc.Vector2
import kotlin.random.Random

class Tetromino(private var pos: Vector2,
                shapeIndex: Int,
                var size: Int,
                val color: Color,
                bord: Bord,
                private val tetrominoList: ArrayList<Tetromino>) {

    private var shape: Array<Array<Boolean>>
    private val waitTime = 0.35f
    private var delta: Float = waitTime
    private var isMoving: Boolean = true
    private val bordShape: Array<Array<Boolean>>
    private val bordColor: Array<Array<Color>>
    //private val length: Int

    init {
        this.shape = setShape(shapeIndex)
        this.bordShape = bord.getBord
        this.bordColor = bord.getBordColor
    }

    companion object{
        private val colors = arrayOf(kColor(42u, 98u, 143u, 255u), kColor(193u, 41u, 48u, 255u), kColor(165u, 153u, 181u, 255u), kColor(78u, 147u, 122u, 255u))
        fun spawn(tetrominoList: ArrayList<Tetromino>, bord: Bord){
            val shape = Random.nextInt(1, 8)
            val color = colors[Random.nextInt(colors.size)]
            tetrominoList.add(
                Tetromino(
                    kVector2(0f, -40f),
                    shape,
                    MySingleton.SIZE,
                    color,
                    bord,
                    tetrominoList
                )
            )
        }
    }

    private fun setShape(shape: Int): Array<Array<Boolean>> {
        when(shape){
            //  [][]
            //[][]
            1-> return arrayOf(
                arrayOf(false, true, true),
                arrayOf(true, true, false))
            //[][]
            //[][]
            2-> return arrayOf(
                arrayOf(true, true),
                arrayOf(true, true))
            //  []
            //[][][]
            3-> return arrayOf(
                arrayOf(false, true, false),
                arrayOf(true, true, true))
            //[][][][]
            4-> return arrayOf(
                arrayOf(true, true, true, true))
            //[]
            //[][][]
            5-> return arrayOf(
                arrayOf(true, false, false),
                arrayOf(true, true, true))
            //[][]
            //  [][]
            6-> return arrayOf(
                arrayOf(true, true, false),
                arrayOf(false, true, true))
            //    []
            //[][][]
            7-> return arrayOf(
                arrayOf(false, false, true),
                arrayOf(true, true, true))
        }
        return arrayOf(arrayOf())
    }

    fun update(){
        if (!isMoving){
            copy()
            tetrominoList.remove(this)
            //println("bye")
            return
        }
        if (isKeyPressed(KeyboardKey.W) || isKeyPressed(KeyboardKey.UP)){
            rotate()
            return
        }
        if (isKeyPressed(KeyboardKey.SPACE)){
            var vmPos = kVector2(pos.x, pos.y + size)
            while (checkAhead(vmPos, shape)){
                pos.y += size
                vmPos = kVector2(pos.x, pos.y + size)
            }
            isMoving = false
            return
        }
        if (isKeyPressed(KeyboardKey.A) || isKeyPressed(KeyboardKey.LEFT)){
            val vmPos = kVector2(pos.x - size, pos.y)
            if(checkAhead(vmPos, shape)){
                pos.x -= size
                return
            }
        }
        if (isKeyPressed(KeyboardKey.D) || isKeyPressed(KeyboardKey.RIGHT)){
            val vmPos = kVector2(pos.x + size, pos.y)
            if(checkAhead(vmPos, shape)){
                pos.x += size
                return
            }
        }

        if (delta <= 0){
            val vmPos = kVector2(pos.x, pos.y + size)
            if(!checkAhead(vmPos, shape)){
                isMoving = false
                return
            }
            pos.y += size
            delta = waitTime
        }else{
            delta -= getFrameTime()
        }
        //println(delta)
    }

    private fun transpose(){
        val novArr = Array(shape[0].size) {Array(shape.size) {false} }
        for (i in shape.indices){
            for (j in shape[i].indices){
                novArr[j][i] = shape[i][j]
            }
        }
        shape = novArr
    }

    private fun rotate(){
        val novArr = Array(shape[0].size) {Array(shape.size) {false} }
        for (i in shape.indices){
            for (j in shape[i].indices){
                novArr[j][novArr[j].size - 1 - i] = shape[i][j]
            }
        }
        val vmPos = kVector2(pos.x, pos.y)
        if (checkAhead(vmPos, novArr)){
            shape = novArr
        }
    }

    private fun copy(){
        println("start")
        val vmPos = kVector2(pos.x, pos.y)
        val zacX = pos.x
        for(i in shape.indices){
            //println("hello $i")
            for (j in shape[i].indices){
                //println(vmPos.y)
                if (vmPos.y < 0){
                    continue
                }
                val indexY = posToIndex(vmPos.y)
                val indexX = posToIndex(vmPos.x)
                if (shape[i][j]){
                    println("hy $indexX $indexY")
                    bordShape[indexY][indexX] = true
                    bordColor[indexY][indexX] = color
                }
                vmPos.x += size
            }
            vmPos.y += size
            vmPos.x = zacX
        }
    }

    private fun checkAhead(vmPos: Vector2, tetroShape: Array<Array<Boolean>>): Boolean{
        val zacX = vmPos.x
        if (posToIndex(vmPos.x) < 0){
            return false
        }
        for(i in tetroShape.indices){
            if (posToIndex(vmPos.y) >= bordShape.size){
                return false
            }
            if (vmPos.y < 0){
                continue
            }
            for (j in tetroShape[i].indices){
                //println("hello $i $j")
                if (posToIndex(vmPos.x) >= bordShape[0].size){
                    return false
                }
                //println(vmPos.y)
                val indexY = posToIndex(vmPos.y)
                val indexX = posToIndex(vmPos.x)
                if (tetroShape[i][j] && bordShape[indexY][indexX]) {
                    //println("false")
                    return false
                }
                vmPos.x += size
            }
            vmPos.y += size
            vmPos.x = zacX
        }
        //println("true")
        return true
    }

    private fun posToIndex(pos: Float): Int{
        return (pos / size).toInt()
    }

    fun draw(offset: Vector2 = kVector2(0f,0f)){
        val vmPos = kVector2(pos.x + offset.x, pos.y + offset.y)
        val zacX = vmPos.x
        for(i in shape.indices){
            //println("hello $i")
            for (j in shape[i].indices){
                //println("$i, $j")
                //println("${vmPos.x}, ${vmPos.y}")
                if (shape[i][j]) {
                    drawRectangle(vmPos, kVector2(size.toFloat(), size.toFloat()), color)
                }
                vmPos.x += size
            }
            vmPos.y += size
            vmPos.x = zacX
        }
    }

    override fun toString(): String{
        var str = ""
        for(i in this.shape.indices){
            for (j in this.shape[i].indices){
                str += if(this.shape[i][j]) "T " else "F "
            }
            str += "\n"
        }
        return str
    }
}