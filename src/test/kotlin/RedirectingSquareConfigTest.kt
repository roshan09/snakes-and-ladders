import RedirectingEntity.LADDER
import RedirectingEntity.SNAKE
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class RedirectingSquareConfigTest{

    @Test
    internal fun `should validate the redirecting square`() {
        RedirectingSquareConfig(0, 4, SNAKE).isValid() shouldBe false
        RedirectingSquareConfig(6, 4, SNAKE).isValid() shouldBe true
        RedirectingSquareConfig(0, 4, LADDER).isValid() shouldBe true
        RedirectingSquareConfig(6, 4, LADDER).isValid() shouldBe false
    }
}