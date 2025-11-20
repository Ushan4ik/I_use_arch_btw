package ru.tbank.education.school.lesson2.examples

open class Password(var value: String) {
    fun isCorrect() = value == "cat123"
    open fun passwordToString() = "*******"
}

// теперь при логировании будем выводить реальное значение, это не безопасно
class EvilPassword(value: String) : Password(value) {
    override fun passwordToString() = value  // тут — утечка!
}

fun log(password: Password) {
    println(password.passwordToString())
}
