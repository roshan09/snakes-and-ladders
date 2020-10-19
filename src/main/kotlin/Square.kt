
sealed class Square(val id: Int)
class NormalSquare(id: Int) : Square(id)
class RedirectingSquare(id: Int, val destination: Square, val type: RedirectingEntity) : Square(id)


data class RedirectingSquareConfig(val id: Int, val destId: Int, val type: RedirectingEntity) {

    fun isValid(): Boolean {
        return type.isValid(id, destId)
    }
}

enum class RedirectingEntity {
    SNAKE {
        override fun isValid(start: Int, end: Int): Boolean {
            return start > end
        }
    },
    LADDER{
        override fun isValid(start: Int, end: Int): Boolean {
            return start < end
        }
    };

    abstract fun isValid(start: Int, end: Int): Boolean
}