
@ThreadLocal
object MySingleton {
    const val SIZE = 40
    const val SCREEN_WIDTH = SIZE * 10
    const val SCREEN_HEIGHT = 800
    var score = 0

    enum class GameState{
        START,
        RUNNING,
        PAUSED,
        GAME_OVER
    }

    var gameState = GameState.START

}