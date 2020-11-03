import GameStatus.DRAW
import GameStatus.WINNER
import java.util.logging.Logger

class Game {
    private val players: Array<Player>
    private val board: Board
    private val dice: Dice
    private val rules: List<Rule>

    constructor(players: Array<Player>, board: Board, dice: Dice, rules: List<Rule>) {
        this.players = players
        this.board = board
        this.dice = dice
        this.rules = rules
    }

    private val logger = Logger.getLogger(javaClass.name)
    private val numberOfTurnsToSkipMap = mutableMapOf<Player, Int>()

    fun start(limitOfTurns : Int): GameResult {

        var currPlayerIndex = 0
        var iteration = 0;

        while (iteration < limitOfTurns) {

            val currPlayer = players[currPlayerIndex]
            val turnsToSkipForCurrPlayer = numberOfTurnsToSkipMap[currPlayer] ?: 0
            if (turnsToSkipForCurrPlayer == 0) {
                logger.info("Playing.. Player : " + currPlayer.id)
                val position = currPlayer.play(dice, board)
                if (checkForWinner(currPlayer))
                    return GameResult(WINNER, currPlayer)
                checkForRulesAndUpdateState(position, currPlayer)
            } else
                numberOfTurnsToSkipMap[currPlayer] = numberOfTurnsToSkipMap[currPlayer]!! - 1;

            if (currPlayerIndex == players.lastIndex) iteration++
            currPlayerIndex = (currPlayerIndex + 1) % players.size

        }
        return GameResult(DRAW)
    }

    private fun checkForWinner(currPlayer: Player): Boolean {
        if (board.playerReachedEnd(currPlayer)) {
            logger.info("Player Id " + currPlayer.id + " reached final square")
            return true
        }
        return false
    }

    private fun checkForRulesAndUpdateState(position: Square, currPlayer: Player) {
        val numberOfTurnsToSkip = rules.map { it.findNumberOfTurnsToSkip(position) }.firstOrNull { it > 0 } ?: 0
        numberOfTurnsToSkipMap[currPlayer] = numberOfTurnsToSkip
        logger.info("Skipping $numberOfTurnsToSkip for ${currPlayer.id}")
    }
}

data class GameResult(val status: GameStatus, val player: Player? = null)

enum class GameStatus {
    WINNER,
    DRAW
}