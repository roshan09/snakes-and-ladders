class Game {
    private val players: Array<Player>
    private val board: Board
    private val dice: Dice

    constructor(players: Array<Player>, board: Board, dice: Dice) {
        this.players = players
        this.board = board
        this.dice = dice
    }
}