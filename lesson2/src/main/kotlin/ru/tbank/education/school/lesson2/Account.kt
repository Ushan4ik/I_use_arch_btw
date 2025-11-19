package ru.tbank.education.school.lesson2

import HomeWork.Song

open class Account
    (
    val id: String,

    var balance: Double,

    val customerID: String,
    SongList: MutableList<Song>
)   {
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
        customerID,
        SongList = mutableListOf<Song>()

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

    open fun addNewSong(song: Song) {}
}