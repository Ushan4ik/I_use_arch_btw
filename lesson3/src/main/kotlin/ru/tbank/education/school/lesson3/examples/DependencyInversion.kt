
// Плохое решение
// Проблемы:
// хотим перейти на Telegram-бота или email → надо лезть внутрь HomeworkReminder и переписывать код;
// в тестах нельзя подставить «фейкового отправителя», придётся реально слать SMS или эмулировать его.

class SmsSender {
    fun send(phone: String, text: String) {
        println("Отправляю SMS на $phone: $text")
    }
}

class HomeworkReminder {

    private val sender = SmsSender()   // жёсткая зависимость

    fun remind(phone: String, homeworkText: String) {
        val message = "Не забудь сделать: $homeworkText"
        sender.send(phone, message)
    }
}




// Хорошее решение
interface MessageSender {
    fun send(to: String, text: String)
}

class SmsSender : MessageSender {
    override fun send(to: String, text: String) {
        println("SMS на $to: $text")
    }
}

class EmailSender : MessageSender {
    override fun send(to: String, text: String) {
        println("Email на $to: $text")
    }
}

class HomeworkReminder(
    private val sender: MessageSender   // зависимость приходит снаружи
) {
    fun remind(to: String, homeworkText: String) {
        val message = "Не забудь сделать: $homeworkText"
        sender.send(to, message)
    }
}
