import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class DiceTest {

    @Test
    internal fun `should return random number lte size`() {
        val random = mockk<RandomIndexGenerator>() {
            every { random(3) } returnsMany listOf(0, 1, 2)
        }
        val normalDice = NormalDice(3, random)
        normalDice.roll() shouldBe  1
        normalDice.roll() shouldBe  2
        normalDice.roll() shouldBe  3
    }

    @Test
    internal fun `should return random even number lte size`() {
        val random = mockk<RandomIndexGenerator>() {
            every { random(3) } returnsMany listOf(0, 1, 2)
        }
        val normalDice = CrookedDice(6, random)

        normalDice.roll() shouldBe 2
        normalDice.roll() shouldBe 4
        normalDice.roll() shouldBe 6
    }
}