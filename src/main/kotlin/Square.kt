
sealed class Square(val id: Int)
class NormalSquare(id: Int) : Square(id)
class RedirectingSquare(id: Int, val destination: Square) : Square(id)


data class RedirectingSquareConfig(val id : Int, val destId : Int)