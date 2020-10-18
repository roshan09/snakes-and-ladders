import java.util.logging.Logger

class Board {
    private val squares: Array<Square>
    private val size: Int

    constructor(size: Int, redirectionConfigs: List<RedirectingSquareConfig>) {
        this.size = size
        this.squares = Array(size) { NormalSquare(it) }
        redirectionConfigs.forEach { this.squares[it.id] = RedirectingSquare(it.id, squares[it.destId]) }
    }

    private val logger = Logger.getLogger(javaClass.name)

    fun findNextPosition(currPosition: Square, number: Int): Square {
        val nextNumber = currPosition.id + number
        if (nextNumber >= size)
            return currPosition
        val nextPosition = squares[nextNumber]
        if (nextPosition is RedirectingSquare) {
            logger.info("Landed on redirecting square with id : ${nextPosition.id}, now going to ${nextPosition.destination.id}")
            return nextPosition.destination
        }
        return nextPosition
    }

    fun playerReachedEnd(player: Player): Boolean {
        val currPos = player.findCurrPosition()
        return currPos.id == size - 1
    }

    fun findFirstSquare(): Square {
        return squares.first()
    }

}