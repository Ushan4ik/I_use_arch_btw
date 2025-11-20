package ru.tbank.education.school.lesson2.bank

class Bank {
    // для создания уникального id
    var accountSeq = 1
    var clientSeq = 1

    private val clients: MutableList<Client> = mutableListOf()
    private val accounts: MutableList<Account> = mutableListOf()

    fun addClient(clientFullName: String) {
        val newClient = Client(
            // конструкция ${clientSeq} позволяет подставлять значения прямо в строку
            "C-${clientSeq}",
             clientFullName
        )
        clientSeq++
        clients.add(newClient)
    }

    fun addAccount(clientId: String) {
        val newAccount = Account(
            id = "A-${accountSeq}",
            balance = 0.0,
            customerId = clientId
        )

        accountSeq++
        accounts.add(newAccount)
    }

    fun transfer(fromAccountId: String, toAccountId: String, amount: Double) {
        // find может вернуть null
        // !! выбрасывает ошибку если значение null
        val fromAccount = accounts.find { it.id == fromAccountId }!!
        val toAccount = accounts.find { it.id == toAccountId }!!

        val ok = fromAccount.withdraw(amount)

        if (ok) {
            toAccount.deposit(amount)
        }
    }

}