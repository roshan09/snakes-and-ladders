import DiceType.NORMAL
import RedirectingEntityType
.LADDER
import RedirectingEntityType.SNAKE

fun main() {
    val board = Board(10, listOf(RedirectingSquareConfig(5, 2, SNAKE), RedirectingSquareConfig(3, 7, LADDER)), RedirectingEntityFactory())
    val dice = DiceFactory.provideDice(NORMAL, 6)
    val player1 = Player(1, board.findInitialPosition())
    val game = Game(arrayOf(player1), board, dice, listOf(PrimeNumberSquareRule(1)))
    val result = game.start(10)
    if(result.status == GameResult.WINNER)
        println("winner is "+result.player!!.id)
    else
        println("Game is draw")
}