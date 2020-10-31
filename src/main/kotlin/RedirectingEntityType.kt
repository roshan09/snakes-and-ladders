
enum class RedirectingEntityType {
    SNAKE {
        override fun isValid(start: Int, end: Int): Boolean {
            return start > end
        }
    },
    LADDER{
        override fun isValid(start: Int, end: Int): Boolean {
            return start < end
        }
    },
    GREEN_SNAKE {
        override fun isValid(start: Int, end: Int): Boolean {
            return start > end
        }
    };

    abstract fun isValid(start: Int, end: Int): Boolean
}

data class RedirectingSquareConfig(val id: Int, val destId: Int, val type: RedirectingEntityType) {
    fun isValid(): Boolean {
        return type.isValid(id, destId)
    }
}