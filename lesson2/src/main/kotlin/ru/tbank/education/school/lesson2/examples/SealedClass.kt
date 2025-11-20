package ru.tbank.education.school.lesson2.examples

// на этапе компиляции точно знаем количество наследников
sealed class LoginState

data class Filled(val username: String, val password: String) : LoginState()
data class Error(val message: String) : LoginState()



