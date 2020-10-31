
abstract class AbstractRedirectingEntity {
    protected abstract val start: Square;
    protected abstract var end: Square
    fun findRedirectedSquare(): Square {
        val dest = end
        postRedirectionAction()
        return dest
    }

    fun findStart() = start

    protected open fun postRedirectionAction() {}
}


class Snake(override val start: Square, override var end: Square) : AbstractRedirectingEntity()

class Ladder(override val start: Square, override var end: Square) : AbstractRedirectingEntity()

class GreenSnake(override val start: Square, override var end: Square) : AbstractRedirectingEntity() {
    override fun postRedirectionAction() {
        end = start
    }
}