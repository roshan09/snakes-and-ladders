
data class Square(val id: Int, var entity: AbstractRedirectingEntity? = null) {
    fun findDestination(): Square {
        return entity?.let { it.findRedirectedSquare() } ?: this
    }
}


