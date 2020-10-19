import DiceType.NORMAL

fun main() {
    val board = Board(10, listOf(RedirectingSquareConfig(5, 2), RedirectingSquareConfig(7, 3)))
    val dice = DiceFactory.provideDice(NORMAL, 6)
    val player1 = Player(1, board.findFirstSquare())
    val game = Game(10, arrayOf(player1), board, dice)
    val result = game.start()
    if(result.status == GameResult.WINNER)
        println("winner is "+result.player!!.id)
    else
        println("Game is draw")
}