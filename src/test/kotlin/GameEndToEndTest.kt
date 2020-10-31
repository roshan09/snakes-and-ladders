import GameResult.DRAW
import GameResult.WINNER
import RedirectingEntityType.*
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class GameEndToEndTest {

    @Test
    internal fun `should play the game and return result as draw when no player reaches end`() {

        val firstEntity = RedirectingSquareConfig(4, 10, LADDER)
        val secondEntity = RedirectingSquareConfig(12, 8, SNAKE)
        val thirdEntity = RedirectingSquareConfig(9, 15, LADDER)
        val fourthEntity = RedirectingSquareConfig(7, 2, GREEN_SNAKE)
        val board = Board(20, listOf(firstEntity, secondEntity, thirdEntity, fourthEntity), RedirectingEntityFactory())
        val firstPlayer = Player(1, board.findFirstSquare())
        val secondPlayer = Player(2, board.findFirstSquare())
        val thirdPlayer = Player(3, board.findFirstSquare())
        val players = arrayOf(
            firstPlayer,
            secondPlayer,
            thirdPlayer
        )
        val dice = mockk<Dice> {
            every { roll() } returnsMany listOf(3,4,5, 4,2,2, 1,2,2)
        }
        val game = Game(3, players, board, dice)
        val result = game.start()
        result.status shouldBe DRAW

        firstPlayer.findCurrPosition().id shouldBe 3
        secondPlayer.findCurrPosition().id shouldBe 10
        thirdPlayer.findCurrPosition().id shouldBe 15
    }

    @Test
    internal fun `should play the game and return player3 as winner when it reaches end`() {

        val firstEntity = RedirectingSquareConfig(4, 10, LADDER)
        val secondEntity = RedirectingSquareConfig(12, 8, SNAKE)
        val thirdEntity = RedirectingSquareConfig(9, 15, LADDER)
        val fourthEntity = RedirectingSquareConfig(7, 2, GREEN_SNAKE)
        val board = Board(17, listOf(firstEntity, secondEntity, thirdEntity, fourthEntity), RedirectingEntityFactory())
        val firstPlayer = Player(1, board.findFirstSquare())
        val secondPlayer = Player(2, board.findFirstSquare())
        val thirdPlayer = Player(3, board.findFirstSquare())
        val players = arrayOf(
            firstPlayer,
            secondPlayer,
            thirdPlayer
        )
        val dice = mockk<Dice> {
            every { roll() } returnsMany listOf(3,4,5, 4,2,2, 1,2,2, 1,2,2)
        }
        val game = Game(4, players, board, dice)
        val result = game.start()
        result.status shouldBe WINNER

        firstPlayer.findCurrPosition().id shouldBe 10
        secondPlayer.findCurrPosition().id shouldBe 8
        thirdPlayer.findCurrPosition().id shouldBe 17
    }
}