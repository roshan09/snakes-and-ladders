import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class HelperTest{

    @Test
    internal fun `should check if num is even`() {
        2.isEven() shouldBe true
        1.isEven() shouldBe false
    }

    @Test
    internal fun `should check if num is prime`() {
        5.isPrime() shouldBe true
        10.isPrime() shouldBe false
    }
}