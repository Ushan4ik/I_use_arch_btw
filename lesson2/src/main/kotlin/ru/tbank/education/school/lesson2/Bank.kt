package ru.tbank.education.school.lesson2


class Bank {
    var clientCount: Int = 1
    var accountSeq: Int = 1
    private val customer: MutableList<Client> = mutableListOf()
    private val accounts: MutableList<Account> = mutableListOf()

    fun addClient(clientFullName: String) {
        val newClient = Client(
            id = "C-${clientCount}",
            fullName = clientFullName
        )
        clientCount++

    }

    fun addAccount(ClientId: String)
    {

        val newAccount = Account(
            id ="A-${accountSeq++}",
            balance = 0.0,
            customerID=ClientId
        )
        accounts.add(newAccount)
    }

    fun transferAccount(fromAccountId: String, toAccountId: String,amount: Double) {

        val fromAccount = accounts.find { it.id == fromAccountId }!!
        val toAccount = accounts.find { it.id == toAccountId }!!

        val ok = fromAccount.withdraw(amount)
        if(ok)
        {
            toAccount.deposit(amount)
        }
    }
}