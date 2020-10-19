import DiceType.NORMAL
import RedirectingEntity.LADDER
import RedirectingEntity.SNAKE

fun main() {
    val board = Board(10, listOf(RedirectingSquareConfig(5, 2, SNAKE), RedirectingSquareConfig(3, 7, LADDER)))
    val dice = DiceFactory.provideDice(NORMAL, 6)
    val player1 = Player(1, board.findFirstSquare())
    val game = Game(10, arrayOf(player1), board, dice)
    val result = game.start()
    if(result.status == GameResult.WINNER)
        println("winner is "+result.player!!.id)
    else
        println("Game is draw")
}