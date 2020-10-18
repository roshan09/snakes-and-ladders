import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class GameTest {
    @Test
    internal fun `should play game and return result`() {
        val board = mockk<Board>()
        val dice = mockk<Dice>()
        val firstPlayer = mockk<Player>() {
            every { id } returns 1
        }
        val secondPlayer = mockk<Player>() {
            every { id } returns 2
        }
        val players = arrayOf(firstPlayer, secondPlayer)
        val game = Game(players, board, dice)

        every { firstPlayer.play(dice, board) } returnsMany listOf(Unit, Unit)
        every { secondPlayer.play(dice, board) } returnsMany listOf(Unit, Unit)

        every { board.playerReachedEnd(firstPlayer) } returnsMany listOf(false, false)
        every { board.playerReachedEnd(secondPlayer) } returnsMany listOf(false, true)

        val result = game.start()

        result shouldBe Result(secondPlayer)
    }
}