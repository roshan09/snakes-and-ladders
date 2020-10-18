import java.util.logging.Logger

class Game {
    private val players: Array<Player>
    private val board: Board
    private val dice: Dice

    constructor(players: Array<Player>, board: Board, dice: Dice) {
        this.players = players
        this.board = board
        this.dice = dice
    }

    private val logger = Logger.getLogger(javaClass.name)

    fun start() {
        var currPlayerIndex = 0

        while (true) {
            val currPlayer = players[currPlayerIndex]
            logger.info("Playing.. " + currPlayer.id)
            currPlayer.play(dice, board)
            if (board.playerReachedEnd(currPlayer)) {
                logger.info("Player Id "+ currPlayer.id + " reached final square")
                break
            }
            currPlayerIndex = (currPlayerIndex + 1) % players.size
        }
    }
}