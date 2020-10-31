import io.kotlintest.shouldBe
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class SquareTest{

    @Test
    internal fun `should find final destination if redirecting entity is present`() {
        val start = mockk<Square>()
        val end = mockk<Square>()
        val square = Square(1, Snake(start, end))
        square.findDestination() shouldBe end
    }

    @Test
    internal fun `should return same square as final destination if redirecting entity is not present`() {
        val square = Square(1)
        square.findDestination() shouldBe square
    }
}