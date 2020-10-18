import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class PlayerTest {

    lateinit var player: Player
    lateinit var dice: Dice
    lateinit var board: Board
    lateinit var initialPosition: Square

    @BeforeEach
    internal fun setUp() {
        dice = mockk()
        board = mockk()
        initialPosition = NormalSquare(1)
        player = Player(1, initialPosition)
    }

    @Test
    internal fun `player should play and update current position`() {
        every { dice.roll() } returns 4
        val nextPosition = NormalSquare(5)
        every { board.findNextPosition(initialPosition, 4) } returns nextPosition
        player.play(dice, board)

        val result = player.findCurrPosition()
        result.id shouldBe nextPosition.id
    }

    @Test
    internal fun `should return current position`() {
        val result = player.findCurrPosition()
        result.id shouldBe initialPosition.id
    }
}