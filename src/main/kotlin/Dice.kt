import kotlin.random.Random

abstract class Dice {
    protected abstract val possibilities: Array<Int>
    private val randomIndexGenerator: RandomIndexGenerator

    constructor(randomIndexGenerator: RandomIndexGenerator) {
        this.randomIndexGenerator = randomIndexGenerator
    }

    fun roll(): Int {
        val index = randomIndexGenerator.random(possibilities.size)
        return possibilities[index]
    }
}

class RandomIndexGenerator {
    fun random(num: Int): Int {
        return Random.nextInt(num)
    }
}

class NormalDice(size: Int, random: RandomIndexGenerator) : Dice(random) {
    override val possibilities: Array<Int> = (1..size).toList().toTypedArray()
}

class CrookedDice(size: Int, random: RandomIndexGenerator) : Dice(random) {
    override val possibilities: Array<Int> = (1..size).filter { it.isEven() }.toTypedArray()
}

fun Int.isEven(): Boolean {
    return this % 2 == 0
}