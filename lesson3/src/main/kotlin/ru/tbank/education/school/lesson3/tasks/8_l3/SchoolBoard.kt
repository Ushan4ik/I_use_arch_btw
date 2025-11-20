open class SchoolBoard(
    val room: String
) {
    protected val textLines = mutableListOf<String>()

    open fun write(text: String) {
        textLines.add(text)
        println("[$room] На доске написано: \"$text\"")
    }

    open fun erase() {
        textLines.clear()
        println("[$room] Доска очищена")
    }

    fun show() {
        println("[$room] Текущее содержимое доски:")
        if (textLines.isEmpty()) {
            println("(пусто)")
        } else {
            textLines.forEach { println("- $it") }
        }
    }
}

class PermanentBoard(
    room: String
) : SchoolBoard(room) {

    override fun erase() {
        throw IllegalStateException("[$room] Это несмываемая доска, стирать нельзя!")
    }
}

fun cleanAllBoards(boards: List<SchoolBoard>) {
    println("Убираемся после уроков, стираем все доски...")
    for (board in boards) {
        board.erase()
    }
}

fun main() {
    val usual = SchoolBoard("Кабинет 101")
    val permanent = PermanentBoard("Коридор")

    usual.write("Домашнее задание: упражнение 5")
    permanent.write("Правила поведения в школе")

    usual.show()
    permanent.show()

    cleanAllBoards(listOf(usual, permanent)) // здесь всё ломается
}
