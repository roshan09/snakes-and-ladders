import io.kotlintest.shouldBe
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class AbstractRedirectingEntityTest {

    @Test
    internal fun `should find final destination for snake`() {
        val end = mockk<Square>()
        val start = mockk<Square>()
        val snake = Snake(start, end)
        snake.findRedirectedSquare() shouldBe end
        snake.findRedirectedSquare() shouldBe end
        snake.findStart() shouldBe start
    }

    @Test
    internal fun `should find final destination for ladder`() {
        val end = mockk<Square>()
        val start = mockk<Square>()
        val ladder = Ladder(start, end)
        ladder.findRedirectedSquare() shouldBe end
        ladder.findRedirectedSquare() shouldBe end
        ladder.findStart() shouldBe start
    }

    @Test
    internal fun `should find final destination for green snake`() {
        val end = mockk<Square>()
        val start = mockk<Square>()
        val greenSnake = GreenSnake(start, end)
        greenSnake.findRedirectedSquare() shouldBe end
        greenSnake.findRedirectedSquare() shouldBe start
        greenSnake.findStart() shouldBe start
    }
}