package ru.tbank.education.school.lesson2
 *
 * В данном классе `BankAccount` реализован метод `transfer()` для перевода денег
 * с одного счета на другой. Однако в текущей реализации присутствует
 * **дедлок (deadlock)** - ситуация взаимной блокировки потоков.
 *
 * Проблема:
 * Дедлок возникает при одновременном выполнении двух переводов в противоположных направлениях:
 *
 * Задание:
 * 1. Проанализируйте код и воспроизведите дедлок с помощью тестов
 * 2. Исправьте метод `transfer()` так, чтобы дедлок был невозможен
 * 3. Убедитесь, что все тесты проходят
 */
fun main() {
    val bankAccount1 = BankAccounts("a", 100000000)
    val bankAccount2 = BankAccounts("b", 100000000)

    val threads = mutableListOf<Thread>()

    repeat(30) {
        threads.add(Thread {
            bankAccount2.transfer(bankAccount1, 100)
        })
        threads.add(Thread {
            bankAccount1.transfer(bankAccount2, 100)
        })
    }

    threads.forEach { it.start() }
    threads.forEach { it.join() }

    println("Final balance account1: ${bankAccount1.balance}")
    println("Final balance account2: ${bankAccount2.balance}")
    println("Total: ${bankAccount1.balance + bankAccount2.balance}")
}

class BankAccounts(val id: String, var balance: Int) {

    fun transfer(to: BankAccounts, amount: Int) {
        // Всегда блокируем в порядке возрастания ID
        val firstLock = if (this.id < to.id) this else to
        val secondLock = if (this.id < to.id) to else this

        synchronized(firstLock) {
            Thread.sleep(10)
            synchronized(secondLock) {
                if (this.balance >= amount) {
                    this.balance -= amount
                    to.balance += amount
                }
            }
        }
    }
}