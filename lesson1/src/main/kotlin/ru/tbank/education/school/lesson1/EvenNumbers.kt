package ru.tbank.education.school.lesson1

/**
 * Сумма четных чисел.

fun sumEvenNumbers(numbers: Array<Int>): Int {
    var count = 0
    for (number in numbers) {
        if (number % 2 == 0)
            count+=number
    }
    return count
}
 */
fun sumEvenNumbers(numbers: Array<Int>) = numbers.filter{it %2==0}.sum()