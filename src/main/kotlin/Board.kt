import java.util.logging.Logger

class Board {
    private val squares: Array<Square>
    private val size: Int

    constructor(size: Int,
                redirectionConfigs: List<RedirectingSquareConfig>,
                redirectingEntityFactory : RedirectingEntityFactory) {
        this.size = size
        this.squares = Array(size + 1) { Square(id = it) }

        val redirectingEntities = redirectingEntityFactory.create(squares, redirectionConfigs)
        redirectingEntities.forEach { it.findStart().entity = it }
    }

    private val logger = Logger.getLogger(javaClass.name)

    fun findNextPosition(currPosition: Square, number: Int): Square {
        val nextNumber = currPosition.id + number
        if (nextNumber > size)
            return currPosition
        val nextPosition = squares[nextNumber]
        logger.info("Next position is ${nextPosition.id}")
        return nextPosition.findDestinationIfRedirectionExist()
    }

    fun playerReachedEnd(player: Player): Boolean {
        val currPos = player.findCurrPosition()
        return currPos.id == size
    }

    fun findInitialPosition(): Square {
        return squares.first()
    }

}