class Board {
    private val squares: Array<Square>
    private val size: Int

    private constructor(size: Int) {
        this.size = size
        this.squares = Array(size) { NormalSquare(it) }
    }
}

