import GameResult.DRAW
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
        val game = Game(10, players, board, dice)

        every { firstPlayer.play(dice, board) } returnsMany listOf(Unit, Unit)
        every { secondPlayer.play(dice, board) } returnsMany listOf(Unit, Unit)

        every { board.playerReachedEnd(firstPlayer) } returnsMany listOf(false, false)
        every { board.playerReachedEnd(secondPlayer) } returnsMany listOf(false, true)

        val result = game.start()

        result shouldBe Result(GameResult.WINNER, secondPlayer)
    }

    @Test
    internal fun `should play game and return result as draw if number of turns cross the limit`() {
        val board = mockk<Board>()
        val dice = mockk<Dice>()
        val firstPlayer = mockk<Player>() {
            every { id } returns 1
        }
        val secondPlayer = mockk<Player>() {
            every { id } returns 2
        }
        val players = arrayOf(firstPlayer, secondPlayer)
        val game = Game(4, players, board, dice)

        every { firstPlayer.play(dice, board) } returnsMany listOf(Unit, Unit, Unit, Unit)
        every { secondPlayer.play(dice, board) } returnsMany listOf(Unit, Unit, Unit, Unit)

        every { board.playerReachedEnd(firstPlayer) } returnsMany listOf(false, false, false, false)
        every { board.playerReachedEnd(secondPlayer) } returnsMany listOf(false, false, false, false)

        val result = game.start()

        result shouldBe Result(DRAW)
    }


}