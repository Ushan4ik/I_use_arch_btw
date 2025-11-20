package ru.tbank.education.school.lesson2.examples

// можем включить и выключить лампу
data class Lamp(var on: Boolean)

// изменить итог чека не можем
data class Receipt(val total: Int)
