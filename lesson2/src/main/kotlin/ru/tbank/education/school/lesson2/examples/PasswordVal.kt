package org.example

class Password(var value: String) {
    fun isCorrect() = value == "cat123"
}

fun main() {
    val password = Password("cat123")

    if (password.isCorrect()) {
        password.value = "cat123456" // можно поменять после проверки — плохо
        // ...какая-то логика...
        // дальше работаем уже с НЕвалидным паролем
    }
}
