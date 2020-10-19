import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class RedirectingEntityTest{

    @Test
    internal fun `should validate redirecting entities`() {
        RedirectingEntity.SNAKE.isValid(0, 7) shouldBe false
        RedirectingEntity.SNAKE.isValid(9, 7) shouldBe true

        RedirectingEntity.LADDER.isValid(0, 7) shouldBe true
        RedirectingEntity.LADDER.isValid(9, 7) shouldBe false
    }
}