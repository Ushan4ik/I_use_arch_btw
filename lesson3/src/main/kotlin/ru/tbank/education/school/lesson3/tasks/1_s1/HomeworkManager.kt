class HomeworkManager {
    private val homeworks = mutableListOf<Pair<String, String>>()  // (предмет, текст)

    fun addHomework(subject: String, text: String) {
        homeworks.add(subject to text) // конструкция to создает Pair<String, String> с (subject, text)
    }

    fun printHomeworks() {
        println("Домашка:")
        for ((subject, text) in homeworks) {
            println("$subject: $text")
        }
    }

    fun sendRemindersToParents(parentsPhones: List<String>) {
        // здесь как будто код для отправки SMS
        for (phone in parentsPhones) {
            println("Отправляю SMS на $phone: Не забудьте проверить домашку!")
        }
    }

    fun saveToFile(filename: String) {
        // логика сохранения в файл
    }
}
