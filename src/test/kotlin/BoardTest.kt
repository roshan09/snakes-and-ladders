import io.kotlintest.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class BoardTest {

    lateinit var board: Board

    @BeforeEach
    fun setUp() {
        board = Board(10)
    }

    @Test
    internal fun `should find next position`() {
        val currPosition = NormalSquare(3);
        val result = board.findNextPosition(currPosition, 3)
        result.id shouldBe 6
    }

    @Test
    internal fun `should not update the position if number is gt size`() {
        val currPosition = NormalSquare(7)
        val result = board.findNextPosition(currPosition, 5)
        result shouldBe currPosition
    }

    @Test
    internal fun `should return true if player is at last square`() {
        val player = Player(1, NormalSquare(9))
        val result = board.playerReachedEnd(player)
        result shouldBe true
    }

    @Test
    internal fun `should return false if player before last square`() {
        val player = Player(1, NormalSquare(8))
        val result = board.playerReachedEnd(player)
        result shouldBe false
    }

    @Test
    internal fun `should return first square`() {
        val result = board.findFirstSquare()
        result.id shouldBe 0
    }

}