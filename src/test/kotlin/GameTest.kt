import GameStatus.DRAW
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
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
        val rule = mockk<Rule>() { every { findNumberOfTurnsToSkip(any()) } returns 0 }
        val game = Game(players, board, dice, listOf(rule))

        every { firstPlayer.play(dice, board) } returnsMany listOf(mockk(), mockk())
        every { secondPlayer.play(dice, board) } returnsMany listOf(mockk(), mockk())

        every { board.playerReachedEnd(firstPlayer) } returnsMany listOf(false, false)
        every { board.playerReachedEnd(secondPlayer) } returnsMany listOf(false, true)

        val result = game.start(10)

        result shouldBe GameResult(GameStatus.WINNER, secondPlayer)
    }

    @Test
    internal fun `should play game and skip the players turn when square is prime`() {
        val board = mockk<Board>()
        val dice = mockk<Dice>()
        val firstPlayer = mockk<Player>() {
            every { id } returns 1
        }
        val secondPlayer = mockk<Player>() {
            every { id } returns 2
        }
        val players = arrayOf(firstPlayer, secondPlayer)

        val nonPrimeSquare1 = mockk<Square>()
        val nonPrimeSquare2 = mockk<Square>()
        val nonPrimeSquare3 = mockk<Square>()
        val primeSquare = mockk<Square>()

        every { firstPlayer.play(dice, board) } returnsMany listOf(nonPrimeSquare1, nonPrimeSquare2, nonPrimeSquare3)
        every { secondPlayer.play(dice, board) } returnsMany listOf(nonPrimeSquare1, primeSquare, nonPrimeSquare3)

        val rule = mockk<Rule> {
            every { findNumberOfTurnsToSkip(nonPrimeSquare1) } returns 0
            every { findNumberOfTurnsToSkip(nonPrimeSquare2) } returns 0
            every { findNumberOfTurnsToSkip(nonPrimeSquare3) } returns 0
            every { findNumberOfTurnsToSkip(primeSquare) } returns 1
        }
        val game = Game(players, board, dice, listOf(rule))
        every { board.playerReachedEnd(firstPlayer) } returnsMany listOf(false, false, false)
        every { board.playerReachedEnd(secondPlayer) } returnsMany listOf(false, false, false)

        val result = game.start(3)

        verify(exactly = 2) { rule.findNumberOfTurnsToSkip(nonPrimeSquare1) }
        verify(exactly = 1) { rule.findNumberOfTurnsToSkip(nonPrimeSquare2) }
        verify(exactly = 1) { rule.findNumberOfTurnsToSkip(nonPrimeSquare3) }
        result shouldBe GameResult(DRAW)
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
        val rule = mockk<Rule>() { every { findNumberOfTurnsToSkip(any()) } returns 0 }
        val game = Game(players, board, dice, listOf(rule))

        every { firstPlayer.play(dice, board) } returnsMany listOf(mockk(), mockk(), mockk(), mockk())
        every { secondPlayer.play(dice, board) } returnsMany listOf(mockk(), mockk(), mockk(), mockk())

        every { board.playerReachedEnd(firstPlayer) } returnsMany listOf(false, false, false, false)
        every { board.playerReachedEnd(secondPlayer) } returnsMany listOf(false, false, false, false)

        val result = game.start(4)

        result shouldBe GameResult(DRAW)
    }


}