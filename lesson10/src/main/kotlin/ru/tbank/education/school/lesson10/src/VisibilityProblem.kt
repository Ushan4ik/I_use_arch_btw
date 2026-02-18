package ru.tbank.education.school.lesson10.src
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
/**
 *
 * Проблема:
 * При оптимизации компилятор и процессор могут переупорядочивать операции
 * или кешировать переменные в регистрах процессора. Это приводит к тому,
 * что изменения переменной в одном потоке могут быть не видны в другом потоке.
 *
 */
class VisibilityProblem {

    private val _running = MutableStateFlow(false)
    val running = _running.asStateFlow()
    /**
     * Создает и возвращает поток writer.
     * Поток выполняет некоторую работу, затем меняет флаг running на false.
     * Изменение может быть не видно потоку reader из-за проблем с видимостью.
     */
    fun startWriter(): Thread {
        return Thread {
            // Имитация работы
            repeat(100) {
                Thread.sleep(10)
                Thread.yield()
            }

            _running.value = false
            println("Writer: установил running = false (изменение может быть не видно)")
        }
    }

    /**
     * Создает и возвращает поток reader.
     * Поток читает флаг running в цикле и может зависнуть навсегда,
     * если не увидит изменение running = false.
     */
    fun startReader(): Thread {
        return Thread {
            println("Reader: начал работу (ждет running = false)")

            while (running.value) {
                Thread.sleep(1) // Небольшая задержка чтобы не нагружать процессор
            }


            println("Reader: завершил работу (увидел running = false)")
        }
    }
}