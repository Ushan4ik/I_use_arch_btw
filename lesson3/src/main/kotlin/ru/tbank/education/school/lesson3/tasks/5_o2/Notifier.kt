// если я захочу добавить другой тип отправки
// то придется изменять изменять написанный класс
// так не хочется делать
class ParentNotifier {

    fun sendSms(to: String, text: String) {
        println("Отправляю SMS на $to: $text")
    }

    fun sendEmail(to: String, text: String) {
        println("Отправляю Email на $to: $text")
    }

    fun sendTelegram(to: String, text: String) {
        println("Отправляю сообщение в Telegram пользователю $to: $text")
    }
}
