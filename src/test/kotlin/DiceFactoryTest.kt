import DiceType.CROOKED
import DiceType.NORMAL
import io.kotlintest.matchers.instanceOf
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class DiceFactoryTest {

    @Test
    internal fun `should return dice based on type`() {
        DiceFactory.provideDice(NORMAL, 3) shouldBe instanceOf(NormalDice::class)
        DiceFactory.provideDice(CROOKED, 3) shouldBe instanceOf(CrookedDice::class)
    }

}
