fun main() {
    val board = Board(10, listOf(RedirectingSquareConfig(5, 2), RedirectingSquareConfig(7, 3)))
    val dice = CrookedDice(6, RandomIndexGenerator())
    val player1 = Player(1, board.findFirstSquare())
    val player2 = Player(2, board.findFirstSquare())
    val game = Game(arrayOf(player1, player2), board, dice)
    val result = game.start()
    println("winner is "+result.player.id)
}