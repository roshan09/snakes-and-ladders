import java.util.logging.Logger

class Player {
    val id: Int
    private var currPosition: Square

    constructor(id: Int, initialPosition: Square) {
        this.id = id
        this.currPosition = initialPosition
    }

    private val logger = Logger.getLogger(javaClass.name)

    fun play(dice: Dice, board: Board) {
        val num = dice.roll()
        val nextPosition = board.findNextPosition(currPosition, num)
        logger.info("Dice Number : $num, moving from.. ${currPosition.id} to ${nextPosition.id}");
        currPosition = nextPosition
    }

    fun findCurrPosition(): Square {
        return currPosition
    }
}