import java.io.File

fun main() {
    val outputFile = File("errors.txt")

    // Очищаем выходной файл
    outputFile.writeText("")

    // Команда для поиска логов
    val findErrorsCommand = """
        find logs logs/old -type f -name "*.log" 2>/dev/null | 
        xargs grep -i "ERROR" 2>/dev/null | 
        sed 's|^logs/||' > '${outputFile.absolutePath}'
    """.trimIndent()

    println("🔍 Поиск строк с 'ERROR' в логах...")

    val exitCode = ProcessBuilder("bash", "-c", findErrorsCommand)
        .inheritIO() // Показывает вывод команд в консоль
        .start()
        .waitFor()

    if (exitCode == 0) {
        val count = if (outputFile.exists()) outputFile.readLines().size else 0
        println("\n✅ Готово! Найдено строк: $count")
        println("📁 Файл: ${outputFile.absolutePath}")
    } else {
        println("❌ Ошибка при выполнении поиска")
    }
}