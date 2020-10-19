import java.util.logging.Logger

class Player(val id: Int, private var currPosition: Square) {

    private val logger = Logger.getLogger(javaClass.name)

    fun play(dice: Dice, board: Board) {
        val num = dice.roll()
        logger.info("Dice Number : $num");
        val nextPosition = board.findNextPosition(currPosition, num)
        logger.info("Moving to : ${nextPosition.id}");
        currPosition = nextPosition
    }

    fun findCurrPosition(): Square {
        return currPosition
    }
}