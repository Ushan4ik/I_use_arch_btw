package ru.tbank.education.school.lesson10.src

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * Задание: Параллельное преобразование элементов списка с использованием async.
 *
 * Преобразуйте каждый элемент списка в отдельной корутине с помощью async.
 *
 * @param items список элементов для преобразования
 * @param transform функция преобразования
 * @return список преобразованных элементов в исходном порядке
 */

fun main() = runBlocking {
    val numbers = listOf(1, 2, 3 , 4, 5, 6, 7, 8, 9, 10)
    val result = parallelTransform(numbers) {
        delay(100)  // имитация долгой работы
        it * it
    }
    println("Результат: $result") // Добавим вывод результата
}

suspend fun <T, R> parallelTransform
(
items: List<T>,
transform: suspend (T) -> R
):
    List<R> = coroutineScope {
    items.map { item -> async { transform(item) }
    }.awaitAll()
}