package ru.tbank.education.school.lesson10.src

class BankAccount(val id: String, var balance: Int) {
    fun transfer(to: BankAccount, amount: Int) {
        // Всегда блокируем счета в порядке возрастания ID
        val firstLock = if (this.id < to.id) this else to
        val secondLock = if (this.id < to.id) to else this

        synchronized(firstLock) {
            Thread.sleep(10) // Имитация работы (можно убрать для тестов)
            synchronized(secondLock) {
                if (this.balance >= amount) {
                    this.balance -= amount
                    to.balance += amount
                }
            }
        }
    }
}