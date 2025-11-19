package HomeWork

open class MusicAccount(
    val id: String,
    val name: String,
    var SongList: MutableList<Song> = mutableListOf(),
    var balance: Double,
    val customerID: String
) {
    fun deposit(amount: Double) {
        balance += amount
    }

    open fun addNewSong(song: Song) {
        SongList.add(song)
    }

    open fun withdraw(amount: Double): Boolean {
        if (balance >= amount) {
            balance -= amount
            return true
        }
        return false
    }
}

class GroupAccount(
    id: String,
    name: String,
    SongList: MutableList<Song> = mutableListOf(),
    balance: Double,
    val groupAccountIDs: MutableList<String> = mutableListOf(),
    val groupMemberNames: MutableList<String> = mutableListOf(),
    customerID: String
) : MusicAccount(
    id = id,
    name = name,
    SongList = SongList,
    balance = balance, customerID
) {
    override fun addNewSong(song: Song) {
        SongList.add(song)
    }

    fun addGroupMember(accountId: String, memberName: String) {
        groupAccountIDs.add(accountId)
        groupMemberNames.add(memberName)
    }

    fun removeGroupMember(accountId: String) {
        val index = groupAccountIDs.indexOf(accountId)
        if (index != -1) {
            groupAccountIDs.removeAt(index)
            groupMemberNames.removeAt(index)
        }
    }


    fun getGroupInfo(): String {
        return "Группа '$name': ${groupMemberNames.size} участников"
    }
}