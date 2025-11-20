data class Lesson(val subject: String, val time: String)

class TimetableEditor {
    private val lessons = mutableListOf<Lesson>()

    fun addLesson(subject: String, time: String) {
        lessons.add(Lesson(subject, time))
    }

    fun removeLesson(subject: String) {
        lessons.removeIf { it.subject == subject }
    }

    fun hasGaps(): Boolean {
        // очень условная проверка «дырок»
        // считаем, что если уроков меньше 4 за день — есть "дыры"
        return lessons.size < 4
    }

    fun notifyStudentsIfChanged(studentEmails: List<String>) {
        println("Расписание изменено, отправляю письма:")
        for (email in studentEmails) {
            println("Email на $email: Расписание уроков обновлено!")
        }
    }

    fun printTimetable() {
        println("Текущее расписание:")
        for (lesson in lessons) {
            println("${lesson.time}: ${lesson.subject}")
        }
    }
}
