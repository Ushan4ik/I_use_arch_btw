package ru.tbank.education.school.lesson2

import kotlin.String

class MusicStudio {
    var clientCount: Int = 1
    var accountNumber: Int = 1
    private val customer: MutableList<ClientMusician> = mutableListOf()
    private val accounts: MutableList<MusicAccount> = mutableListOf()

    fun addClient(clientFullName: String) {
        val newClient = try{
            ClientMusician(
                id = "C-${clientCount}",
                fullName = clientFullName
            )
        } catch (e: Exception) {
            TODO("Not yet implemented")
        }
        customer.add(newClient)
        clientCount++

    }

    fun addAccount(ClientId: String) {
        val clientExists = customer.any { it.id == ClientId }

        if (clientExists) {


            val newAccount = try {
                MusicAccount(
                    id = "A-${accountNumber++}",
                    balance = 0.0,
                    customerID = ClientId
                )
            } catch (e: Exception) {
                TODO("Not yet implemented")
            }
            accounts.add(newAccount)
        }
    }

    fun CreateSong(AccountID: String, SongPrice:Double,
    Songgenre: String, SongName: String, DurSec :Int){
        val clientExists = accounts.any { it.id == AccountID }
        if (clientExists) {
            val account = accounts.find { it.id == AccountID}
            if(account?.withdraw(SongPrice) ?: false)
            {
                println("Записываем реп, нигга")
                val newSong = try {
                    Song(
                        genre = Songgenre,
                        name = SongName,
                        DurationSec = DurSec

                    )
                } catch (e: Exception) {
                    TODO("Not yet implemented")
                }
                account.addNewSong(newSong)

            }
            else
            {
                println("Слишко мало золота, Милорд")
            }




        }else{

        }

    }
}