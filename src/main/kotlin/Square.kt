
data class Square(val id: Int, var entity: AbstractRedirectingEntity? = null) {
    fun findDestinationIfRedirectionExist(): Square {
        return entity?.let { it.findRedirectedSquare() } ?: this
    }
}


