package ru.tbank.education.school.lesson10

import java.util.concurrent.Executors
import java.util.concurrent.Callable
import java.util.concurrent.Future

object ExecutorServiceExample {
    fun run(): List<String> {
        val executor = Executors.newFixedThreadPool(4)

        try {
            val tasks = List(100) { index ->
                Callable<String> {
                    Thread.sleep(1)
                    "Задача ${index + 1} выполнена в потоке ${Thread.currentThread().name}"
                }
            }

            return executor.invokeAll(tasks).map { it.get() }  // можно сразу здесь
        } finally {
            executor.shutdown()
        }
    }

    fun runfact(): List<Long> {
        val executor = Executors.newFixedThreadPool(4)

        try {
            val tasks = List(20) { index ->
                Callable<Long> {
                    (1..index+1).fold(1L) { acc, next -> acc * next }

                }
            }

            return executor.invokeAll(tasks).map { it.get() }
        } finally {
            executor.shutdown()
        }
    }
}

fun main() {
   val results = ExecutorServiceExample.runfact()
    results.forEach(::println)





    /*val bankAccount1 = BankAccount("a", 100000000)
    val bankAccount2 = BankAccount("b", 100000000)

    val threads = mutableListOf<Thread>()

    repeat(30) {
        threads.add(Thread {
            bankAccount2.transfer(bankAccount1, 100)
        })
        threads.add(Thread {
            bankAccount1.transfer(bankAccount2, 100)
        })
    }
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExample {package ru.tbank.education.school.lesson2


fun main(){

    println(Deadlock.runFixed())
}

object CreateThreads {
    @Synchronized
    fun run(): Int {
        var count = 0
        val lock = Any()
        val threads = mutableListOf<Thread>()
        repeat(10)
        {

            threads.add(Thread {
                println("Current thread: ${Thread.currentThread().name}")
                repeat(1000) {
                    synchronized(lock){count++}
                }
            })


        }



        // Start all threads

        threads.forEach { it.start() }
        threads.forEach { it.join() }
        return count
    }
}


object Deadlock {
    private val resourceA = Any()
    private val resourceB = Any()

    fun runDeadlock() {
        val thread1 = Thread {
            synchronized(resourceA) {
                println("Thread 1: захватил resourceA")
                Thread.sleep(100) // Имитация работы
                println("Thread 1: пытается захватить resourceB...")
                synchronized(resourceB) {
                    println("Thread 1: захватил resourceB")
                }
            }
            println("Thread 1: завершил работу")
        }

        val thread2 = Thread {
            synchronized(resourceB) {
                println("Thread 2: захватил resourceB")
                Thread.sleep(100) // Имитация работы
                println("Thread 2: пытается захватить resourceA...")
                synchronized(resourceA) {
                    println("Thread 2: захватил resourceA")
                }
            }
            println("Thread 2: завершил работу")
        }

        thread1.start()
        thread2.start()

        thread1.join()
        thread2.join()
    }

    fun runFixed(): Boolean {
        var success = false
        val thread1 = Thread {
            synchronized(resourceA) {
                println("Thread 1: захватил resourceA")
                Thread.sleep(100)
                println("Thread 1: пытается захватить resourceB...")
                synchronized(resourceB) {
                    println("Thread 1: захватил resourceB")
                    success = true
                }
            }
            println("Thread 1: завершил работу")
        }

        val thread2 = Thread {
            synchronized(resourceA) { // Теперь захватываем в том же порядке!
                println("Thread 2: захватил resourceA")
                Thread.sleep(100)
                println("Thread 2: пытается захватить resourceB...")
                synchronized(resourceB) {
                    println("Thread 2: захватил resourceB")
                }
            }
            println("Thread 2: завершил работу")
        }

        thread1.start()
        thread2.start()

        thread1.join()
        thread2.join()

        return success
    }*/


}