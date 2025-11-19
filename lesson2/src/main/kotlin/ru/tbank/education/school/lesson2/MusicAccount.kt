package ru.tbank.education.school.lesson2

open class MusicAccount
    (
    val id: String,
    val SongList: MutableList<Song> = mutableListOf(),
    var balance: Double,

    val customerID: String)   {
    fun deposit(amount: Double) {
        balance += amount
    }
    open fun addNewSong(song: Song) {
        SongList.add(song)
    }
    open fun withdraw(amount: Double): Boolean {
        if(balance >= amount) {
            balance -= amount
            return true
        }
        return false
    }



    class GroupAccount(


        id: String,
        AccountsIn: List<String> ,
        SongList: MutableList<Song> = mutableListOf(),
        balance: Double,
        customerID: String,



    ): Account(
        id,
        balance,
        customerID
    ){


        override fun withdraw(amount: Double): Boolean {
            if(creditLimit + balance >= amount) {
                balance -= amount
                return true
            }
            return false
        }
    }
}