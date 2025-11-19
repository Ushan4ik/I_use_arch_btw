package HomeWork

import kotlin.String

class MusicStudio {
    var clientCount: Int = 1
    var accountNumber: Int = 1
    public val customer: MutableList<ClientMusician> = mutableListOf()
    public val accounts: MutableList<MusicAccount> = mutableListOf()

    fun addClient(clientFullName: String) {
        // Проверка, что такой артист уже есть
        val clientExists = customer.any { it.fullName == clientFullName }
        if (clientExists) {
            println("Артист '$clientFullName' уже существует")
            return
        }

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

    fun addAccount(ClientId: String, Name: String) {
        val clientExists = customer.any { it.id == ClientId }

        if (clientExists) {
            // Проверка, что аккаунт с таким именем уже существует
            val accountExists = accounts.any { it.name == Name }
            if (accountExists) {
                println("Аккаунт с именем '$Name' уже существует")
                return
            }

            val newAccount = try {
                MusicAccount(
                    id = "A-${accountNumber++}",
                    name = Name,
                    balance = 0.0,
                    customerID = ClientId
                )
            } catch (e: Exception) {
                TODO("Not yet implemented")
            }
            accounts.add(newAccount)
        }
    }

    fun createGroupAccount(groupName: String, memberAccountIDs: List<String>, creatorClientID: String) {
        // Проверка, что группа с таким названием уже существует
        val groupExists = accounts.any { it.name == groupName }
        if (groupExists) {
            println("Группа с названием '$groupName' уже существует")
            return
        }

        // Проверяем, что все аккаунты участников существуют
        val validMembers = memberAccountIDs.filter { accountID ->
            accounts.any { it.id == accountID }
        }

        if (validMembers.size != memberAccountIDs.size) {
            println("Некоторые аккаунты участников не найдены")
            return
        }

        // Создаем групповой аккаунт
        val newGroupAccount = try {
            GroupAccount(
                id = "A-${accountNumber++}",
                name = groupName,
                balance = 0.0,
                customerID = creatorClientID,
                groupAccountIDs = validMembers.toMutableList(),
                groupMemberNames = mutableListOf()
            )
        } catch (e: Exception) {
            TODO("Not yet implemented")
        }

        // Заполняем имена участников
        validMembers.forEach { accountID ->
            val account = accounts.find { it.id == accountID }
            account?.let {
                newGroupAccount.groupMemberNames.add(it.name)
            }
        }

        accounts.add(newGroupAccount)
        println("Групповой аккаунт '$groupName' создан с ${validMembers.size} участниками")
    }

    // Функция вывода информации по названию
    fun getInfoByName(name: String) {
        println("=== Информация по запросу: '$name' ===")

        // Ищем исполнителя
        val artist = customer.find { it.fullName == name }
        if (artist != null) {
            println("\n🎤 Исполнитель: ${artist.fullName} (ID: ${artist.id})")

            // Находим аккаунты исполнителя
            val artistAccounts = accounts.filter { it.customerID == artist.id }
            if (artistAccounts.isNotEmpty()) {
                println("Аккаунты исполнителя:")
                artistAccounts.forEach { account ->
                    println("  - ${account.name} (ID: ${account.id}) Баланс: ${account.balance}")
                    println("    Песни: ${account.SongList.size} шт")
                }
            }

            // Находим группы, где участвует исполнитель
            val artistGroups = accounts.filterIsInstance<GroupAccount>()
                .filter { group ->
                    group.groupAccountIDs.any { accountID ->
                        artistAccounts.any { it.id == accountID }
                    }
                }

            if (artistGroups.isNotEmpty()) {
                println("Участвует в группах:")
                artistGroups.forEach { group ->
                    println("  - ${group.name} (ID: ${group.id})")
                    println("    Участников: ${group.groupAccountIDs.size}")
                }
            } else {
                println("Не участвует в группах")
            }
        }

        // Ищем группу
        val group = accounts.find { it.name == name && it is GroupAccount } as? GroupAccount
        if (group != null) {
            println("\n🎵 Группа: ${group.name} (ID: ${group.id})")
            println("Баланс: ${group.balance}")
            println("Участники (${group.groupMemberNames.size}):")
            group.groupMemberNames.forEachIndexed { index, memberName ->
                val accountID = group.groupAccountIDs.getOrNull(index)
                println("  - $memberName (ID аккаунта: $accountID)")
            }
            println("Песни группы: ${group.SongList.size} шт")
        }

        // Если ничего не найдено
        if (artist == null && group == null) {
            println("Ничего не найдено по запросу '$name'")
        }
        println("=====================================")
    }

    fun CreateSong(AccountID: String, SongPrice: Double,
                   Songgenre: String, SongName: String, DurSec: Int) {
        val clientExists = accounts.any { it.id == AccountID }
        if (clientExists) {
            val account = accounts.find { it.id == AccountID}
            if(account?.withdraw(SongPrice) ?: false) {
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
                account?.addNewSong(newSong)
            } else {
                println("Слишко мало золота, Милорд")
            }
        }
    }
}