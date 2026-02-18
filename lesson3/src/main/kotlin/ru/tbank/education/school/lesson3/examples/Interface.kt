import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle

interface Saveable {
    fun save()
}

class UserSettings(
    var theme: String,
    var notificationsEnabled: Boolean
) : Saveable {

    override fun save() {
        println("Сохраняю настройки: theme=$theme, notifications=$notificationsEnabled")
    }
}

class MonthlyReport(
    val title: String,
    val totalIncome: Long,
) : Saveable {
    override fun save() {
        println("Сохраняю отчёт \"${HtmlStyle.title}\" с доходом $totalIncome")
    }
}

class GameStatistics(
    val playerName: String,
    val score: Int
) : Saveable {

    override fun save() {
        println("Сохраняю статистику игрока $playerName: $score очков")
    }
}



fun saveAll(items: List<Saveable>) {
    for (item in items) {
        item.save()
    }
}

fun main() {
    val settings = UserSettings("dark", true)
    val report = MonthlyReport("Март 2025", 1_000_000)
    val stats = GameStatistics("PlayerOne", 9000)

    saveAll(listOf(settings, report, stats))
}
