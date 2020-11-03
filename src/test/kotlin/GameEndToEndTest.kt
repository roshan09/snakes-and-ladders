import GameStatus.DRAW
import GameStatus.WINNER
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
        val firstPlayer = Player(1, board.findInitialPosition())
        val secondPlayer = Player(2, board.findInitialPosition())
        val thirdPlayer = Player(3, board.findInitialPosition())
        val players = arrayOf(
            firstPlayer,
            secondPlayer,
            thirdPlayer
        )
        val dice = mockk<Dice> {
            every { roll() } returnsMany listOf(3,4,5, 4,2,2, 1,2,2)
        }
        val game = Game(players, board, dice, listOf(PrimeNumberSquareRule(0)))
        val result = game.start(3)
        result.status shouldBe DRAW
        result.player shouldBe null

        firstPlayer.findCurrPosition().id shouldBe 3
        secondPlayer.findCurrPosition().id shouldBe 10
        thirdPlayer.findCurrPosition().id shouldBe 15
    }

    @Test
    internal fun `should play the game and skip the player turn if player lands on prime number square`() {

        val board = Board(20, listOf(), RedirectingEntityFactory())
        val firstPlayer = Player(1, board.findInitialPosition())
        val secondPlayer = Player(2, board.findInitialPosition())
        val thirdPlayer = Player(3, board.findInitialPosition())
        val players = arrayOf(
            firstPlayer,
            secondPlayer,
            thirdPlayer
        )
        val dice = mockk<Dice> {
            every { roll() } returnsMany listOf(6,4,4, 1,2,3, 2, 1,1,1)
        }
        val game = Game(players, board, dice, listOf(PrimeNumberSquareRule(1)))
        val result = game.start(4)
        result.status shouldBe DRAW
        result.player shouldBe null

        firstPlayer.findCurrPosition().id shouldBe 8
        secondPlayer.findCurrPosition().id shouldBe 9
        thirdPlayer.findCurrPosition().id shouldBe 8
    }

    @Test
    internal fun `should play the game and return player3 as winner when it reaches end`() {

        val firstEntity = RedirectingSquareConfig(4, 10, LADDER)
        val secondEntity = RedirectingSquareConfig(12, 8, SNAKE)
        val thirdEntity = RedirectingSquareConfig(9, 15, LADDER)
        val fourthEntity = RedirectingSquareConfig(7, 2, GREEN_SNAKE)
        val board = Board(17, listOf(firstEntity, secondEntity, thirdEntity, fourthEntity), RedirectingEntityFactory())
        val firstPlayer = Player(1, board.findInitialPosition())
        val secondPlayer = Player(2, board.findInitialPosition())
        val thirdPlayer = Player(3, board.findInitialPosition())
        val players = arrayOf(
            firstPlayer,
            secondPlayer,
            thirdPlayer
        )
        val dice = mockk<Dice> {
            every { roll() } returnsMany listOf(3,4,5, 4,2,2, 1,2,2, 1,2,2)
        }
        val game = Game(players, board, dice, listOf(PrimeNumberSquareRule(0)))
        val result = game.start(4)
        result.status shouldBe WINNER
        result.player shouldBe thirdPlayer

        firstPlayer.findCurrPosition().id shouldBe 10
        secondPlayer.findCurrPosition().id shouldBe 8
        thirdPlayer.findCurrPosition().id shouldBe 17
    }
}