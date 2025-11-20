open class HomeworkSubmission(
    val studentName: String,
    var content: String
) {
    // Считаем, что работу можно отправлять заново: новая версия заменяет старую
    open fun resubmit(newContent: String) {
        content = newContent
        println("Ученик $studentName отправил новую версию работы")
    }
}

class FinalExamSubmission(
    studentName: String,
    content: String
) : HomeworkSubmission(studentName, content) {

    override fun resubmit(newContent: String) {
        // Итоговую работу пересдавать нельзя
        throw IllegalStateException("Итоговую работу ученика $studentName нельзя пересдать")
    }
}

fun allowFixes(submission: HomeworkSubmission) {
    // Учитель позволяет ученику подправить решение и пересдать
    submission.resubmit(submission.content + "\n// исправлено по замечаниям")
}

fun main() {
    val draft = HomeworkSubmission("Аня", "Решение задачи 1")
    val final = FinalExamSubmission("Боря", "Итоговая контрольная работа")

    allowFixes(draft)
    allowFixes(final) // тут всё ломается
}
