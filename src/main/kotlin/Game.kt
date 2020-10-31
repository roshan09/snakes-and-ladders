import GameResult.DRAW
import GameResult.WINNER
import java.util.logging.Logger

class Game {
    private val players: Array<Player>
    private val board: Board
    private val dice: Dice
    private val limitOfTurns: Int

    constructor(limitOfTurns: Int, players: Array<Player>, board: Board, dice: Dice) {
        this.limitOfTurns = limitOfTurns
        this.players = players
        this.board = board
        this.dice = dice
    }

    private val logger = Logger.getLogger(javaClass.name)

    fun start(): Result {

        var currPlayerIndex = 0
        var iteration = 0;

        while (iteration < limitOfTurns) {

            val currPlayer = players[currPlayerIndex]
            logger.info("Playing.. Player : " + currPlayer.id)
            currPlayer.play(dice, board)
            if (board.playerReachedEnd(currPlayer)) {
                logger.info("Player Id "    + currPlayer.id + " reached final square")
                return Result(WINNER, currPlayer)
            }

            if (currPlayerIndex == players.lastIndex) iteration++
            currPlayerIndex = (currPlayerIndex + 1) % players.size

        }
        return Result(DRAW)
    }
}

data class Result(val status: GameResult, val player: Player? = null)

enum class GameResult {
    WINNER,
    DRAW
}