class Board {
    private val squares: Array<Square>
    private val size: Int

    constructor(size: Int) {
        this.size = size
        this.squares = Array(size) { NormalSquare(it) }
    }

    fun findNextPosition(currPosition: Square, number: Int): Square {
        val nextNumber = currPosition.id + number
        if (nextNumber >= size)
            return currPosition
        return squares[nextNumber]
    }

    fun playerReachedEnd(player: Player): Boolean {
        val currPos = player.findCurrPosition()
        return currPos.id == size - 1
    }

    fun findFirstSquare(): Square {
        return squares.first()
    }

}