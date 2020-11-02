abstract class Rule {
    protected abstract val numberOfTurns: Int
    protected abstract fun isValid(position: Square): Boolean
    fun findNumberOfTurnsToSkip(position: Square): Int {
        if (isValid(position))
            return numberOfTurns
        return 0
    }
}


class PrimeNumberSquareRule(override val numberOfTurns: Int) : Rule() {
    override fun isValid(position: Square): Boolean {
        return position.isPrimeNumberSquare()
    }
}
