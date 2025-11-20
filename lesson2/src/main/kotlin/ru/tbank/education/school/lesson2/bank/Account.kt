package ru.tbank.education.school.lesson2.bank

open class Account(
    val id: String,
    var balance: Double,
    val customerId: String
) {
    fun deposit(amount: Double) {
        balance += amount
    }

    open fun withdraw(amount: Double): Boolean {
        if (balance >= amount) {
            balance -= amount
            return true
        }
        return false
    }
}

class CreditAccount(
    id: String,
    balance: Double,
    customerId: String,
    creditLimit: Double,
) : Account(
    id,
    balance,
    customerId,
) {
    var creditLimit = creditLimit

    override fun withdraw(amount: Double): Boolean {
      if (creditLimit + balance >= amount) {
          balance -= amount
          return true
      }
        return false
    }
}

