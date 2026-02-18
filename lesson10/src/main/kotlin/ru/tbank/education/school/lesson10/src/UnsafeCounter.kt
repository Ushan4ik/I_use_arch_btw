package ru.tbank.education.school.lesson10.src

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock

/**
 *
 * Задание: Исправьте гонку данных в этом классе любым из известных вам способов
 *
 * Проблема: Несколько корутин одновременно увеличивают счетчик `value`,
 * что приводит к потере некоторых инкрементов из-за race condition.
 */

fun main() = runBlocking {
    val counter = UnsafeCounter()
    val result = counter.runConcurrentIncrements()
    println("Ожидаемое значение: ${10 * 1000} = 10000")
    println("Фактическое значение: $result")
    println("Потеряно инкрементов: ${10000 - result}")
}

class UnsafeCounter {
    private var value = 0
    private val mutex = Mutex()

    // ВАРИАНТ 1: Использование Mutex (рекомендуется для корутин)
    suspend fun increment() {
        mutex.withLock {
            value++
        }
    }
    fun getValue(): Int = value

    suspend fun runConcurrentIncrements(
        coroutineCount: Int = 10,
        incrementsPerCoroutine: Int = 1000
    ): Int = coroutineScope {
        val jobs = List(coroutineCount) {
            launch(Dispatchers.Default) {
                repeat(incrementsPerCoroutine) {
                    increment()
                }
            }
        }
        jobs.joinAll()
        getValue()
    }
}