package ru.tbank.education.school.lesson2.examples

// Если вот так написать, то нельзя будеть наследовать

// class Student(val score: Int) {
//    open fun isExcellent(): Boolean = score >= 90
//}

open class Student(val score: Int) {
    open fun isExcellent(): Boolean = score >= 9
}

class Cheater(score: Int) : Student(score) {
    override fun isExcellent(): Boolean = true  // теперь любой — "отличник"
}