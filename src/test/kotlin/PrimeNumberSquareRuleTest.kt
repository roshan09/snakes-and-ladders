import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class PrimeNumberSquareRuleTest{

    @Test
    internal fun `should validate the prime number rule and return number of turns to skip`() {
        val rule = PrimeNumberSquareRule(2)
        val primeNumberSquare = Square(5)
        val evenNumberSquare = Square(6)
        rule.findNumberOfTurnsToSkip(primeNumberSquare) shouldBe 2
        rule.findNumberOfTurnsToSkip(evenNumberSquare) shouldBe 0
    }
}