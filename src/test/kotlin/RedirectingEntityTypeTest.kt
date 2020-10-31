import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class RedirectingEntityTypeTest{

    @Test
    internal fun `should validate redirecting entities`() {
        RedirectingEntityType.SNAKE.isValid(0, 7) shouldBe false
        RedirectingEntityType.SNAKE.isValid(9, 7) shouldBe true

        RedirectingEntityType.LADDER.isValid(0, 7) shouldBe true
        RedirectingEntityType.LADDER.isValid(9, 7) shouldBe false

        RedirectingEntityType.GREEN_SNAKE.isValid(0, 7) shouldBe false
        RedirectingEntityType.GREEN_SNAKE.isValid(9, 7) shouldBe true
    }
}