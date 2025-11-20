// Абстрактный базовый класс
abstract class FileProcessor(
    val filePath: String
    var data: String
) {

    // Общий для всех способ чтения файла в строку
    fun readFile() {
        val file = java.io.File(filePath)
        if (!file.exists()) {
            throw IllegalArgumentException("Файл $filePath не найден")
        }
        data = file.readText()
    }

    // Абстрактный метод: конкретные обработчики решают, что делать с данными
    abstract fun processData()
}

class CsvFileProcessor(filePath: String) : FileProcessor(filePath)
class YamlFileProcessor(filePath: String) : FileProcessor(filePath)
