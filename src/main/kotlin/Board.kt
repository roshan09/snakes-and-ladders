import java.util.logging.Logger

class Board {
    private val squares: Array<Square>
    private val size: Int

    constructor(size: Int, redirectionConfigs: List<RedirectingSquareConfig>) {
        this.size = size
        this.squares = Array(size + 1) { NormalSquare(it) }
        redirectionConfigs.filter { it.isValid() }.forEach {
            this.squares[it.id] = RedirectingSquare(it.id, squares[it.destId], it.type)
        }
    }

    private val logger = Logger.getLogger(javaClass.name)

    fun findNextPosition(currPosition: Square, number: Int): Square {
        val nextNumber = currPosition.id + number
        if (nextNumber > size)
            return currPosition
        val nextPosition = squares[nextNumber]
        logger.info("Next position is ${nextPosition.id}")
        if (nextPosition is RedirectingSquare) {
            val nextId = nextPosition.destination.id
            logger.info("Landed on redirecting square of type ${nextPosition.type} going from ${nextPosition.id} to $nextId")
            return nextPosition.destination
        }
        return nextPosition
    }

    fun playerReachedEnd(player: Player): Boolean {
        val currPos = player.findCurrPosition()
        return currPos.id == size
    }

    fun findFirstSquare(): Square {
        return squares.first()
    }

}