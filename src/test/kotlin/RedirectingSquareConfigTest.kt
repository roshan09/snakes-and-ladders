import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class RedirectingSquareConfigTest{

    @Test
    internal fun `should validate the redirecting square`() {
        val type = mockk<RedirectingEntityType>(){
            every { isValid(0, 4) } returnsMany listOf(false, true)
        }
        RedirectingSquareConfig(0, 4, type).isValid() shouldBe false
        RedirectingSquareConfig(0, 4, type).isValid() shouldBe true
    }
}