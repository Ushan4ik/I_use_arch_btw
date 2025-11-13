package ru.tbank.education.school.lesson2

open class Account
    (
            val id: String,

            var balance: Double,

            val customerID: String)   {
    fun deposit(amount: Double) {
        balance += amount
    }
    open fun withdraw(amount: Double): Boolean {
        if(balance >= amount) {
            balance -= amount
            return true
        }
        return false
    }

    class CreditAccount(


        id: String,
        balance: Double,
        customerID: String,
        creditLimit: Double

    ): Account(
        id,
        balance,
        customerID
    ){
        var creditLimit = creditLimit

        override fun withdraw(amount: Double): Boolean {
            if(creditLimit + balance >= amount) {
                balance -= amount
                return true
            }
            return false
        }
    }
}