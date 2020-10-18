
object DiceFactory {
    private val randomIndexGenerator = RandomIndexGenerator()
    fun provideDice(type: DiceType, size: Int): Dice {
        return when (type) {
            DiceType.NORMAL -> NormalDice(size, randomIndexGenerator)
            DiceType.CROOKED -> CrookedDice(size, randomIndexGenerator)
        }
    }
}