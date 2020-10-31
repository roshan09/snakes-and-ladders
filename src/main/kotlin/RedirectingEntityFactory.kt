
class RedirectingEntityFactory {

    fun create(
        squares: Array<Square>,
        redirectionConfigs: List<RedirectingSquareConfig>
    ): List<AbstractRedirectingEntity> {
        return redirectionConfigs.filter { it.isValid() }.map { create(it, squares) }
    }

    private fun create(redirectionConfig: RedirectingSquareConfig, squares: Array<Square>): AbstractRedirectingEntity {
        val redirectingEntity = redirectionConfig.type
        val start = squares[redirectionConfig.id]
        val end = squares[redirectionConfig.destId]
        return when (redirectingEntity) {
            RedirectingEntityType.SNAKE -> Snake(start, end)
            RedirectingEntityType.LADDER -> Ladder(start, end)
            RedirectingEntityType.GREEN_SNAKE -> GreenSnake(start, end)
        }
    }
}