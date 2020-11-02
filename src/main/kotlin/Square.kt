
data class Square(val id: Int, var entity: AbstractRedirectingEntity? = null) {
    fun findDestinationIfRedirectionExist(): Square {
        return entity?.let { it.findRedirectedSquare() } ?: this
    }

    fun isPrimeNumberSquare() : Boolean {
        return this.id.isPrime()
    }
}


