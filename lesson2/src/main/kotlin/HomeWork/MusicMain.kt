package HomeWork

fun main() {
    println("Демонстрация работы MusicStudio ")
    println("=" * 50)

    val studio = MusicStudio()

    println("\n1. СОЗДАНИЕ АРТИСТОВ:")
    studio.addClient("Eminem")
    studio.addClient("Талант")
    studio.addClient("Моцарт")

    println("\nПопытка создать дубликат артиста:")
    studio.addClient("Eminem")

    println("\n2. СОЗДАНИЕ АККАУНТОВ:")
    studio.addAccount("C-1", "Eminem Official")
    studio.addAccount("C-2", "Cерёга Пират")
    studio.addAccount("C-3", "ambientcore")

    println("\nПопытка создать аккаунт с существующим именем:")
    studio.addAccount("C-1", "Eminem Official")

    println("\n3. ПОПОЛНЕНИЕ БАЛАНСОВ:")
    val SeregaAc = studio.accounts.find { it.name == "Cерёга Пират"}
    val MochAc = studio.accounts.find { it.name == "ambientcore" }
    val EminemAc = studio.accounts.find { it.name == "Eminem Official" }
    SeregaAc?.deposit(999999.0)
    EminemAc?.deposit(10000.0)

    println("Баланс Серёги: ${SeregaAc?.balance}")
    println("Баланс Eminem: ${EminemAc?.balance}")


    println("\n4. СОЗДАНИЕ ПЕСЕН:")
    studio.CreateSong("A-2", 1000.0, "Rap", "Lose Yourself", 326.0,
        CreateRemix = false)
    studio.CreateSong("A-1", 800.0, "Rap", "The Real Slim Shady", 284.0,
        CreateRemix = false)
    studio.CreateSong("A-2", 1500.0, "Pop-Rock", "Почему ты ещё не фанат?", 304.0,
        CreateRemix = false)

    println("\nПопытка создать песню без денег:")
    studio.CreateSong(
        "A-1", 50000.0, "Rap", "Gin and Juice", 210.0,
        CreateRemix = false
    )

    println("\n5. СОЗДАНИЕ ГРУППЫ:")
    val eminemAccId = EminemAc?.id ?: "wtf"
    val SeregaAccId = SeregaAc?.id ?: "wtf"
    val mochAccId = MochAc?.id ?: "wtf"

    studio.createGroupAccount(
        "колектив DJ MONSTER 2007 ",
        listOf(SeregaAccId, mochAccId),
        "C-2"
    )

    println("\nПопытка создать группу с существующим именем:")
    studio.createGroupAccount(
        "колектив DJ MONSTER 2007 ",
        listOf(SeregaAccId, mochAccId),
        "C-2"
    )

    println("\n6. ПРОВЕРКА ИНФОРМАЦИИ:")

    println("\nа) Информация об исполнителе:")
    studio.getInfoByName("Eminem")


    studio.getInfoByName("Талант")

    studio.getInfoByName("Моцарт")


    println("\nв) Информация об участнике группы:")
    studio.getInfoByName("Eminem")

    println("\nг) Поиск несуществующего:")
    studio.getInfoByName("Non Existent Artist")

    println("\n7. ФИНАЛЬНАЯ ПРОВЕРКА ДАННЫХ:")

    println("\nВсе клиенты:")
    studio.customer.forEach { client ->
        println("- ${client.fullName} (ID: ${client.id})")
    }

    println("\nВсе аккаунты:")
    studio.accounts.forEach { account ->
        when (account) {
            is GroupAccount -> {
                println("- ГРУППА: ${account.name} (ID: ${account.id})")
                println("  Участники: ${account.groupMemberNames}")
                println("  Баланс: ${account.balance}")
            }
            else -> {
                println("- АККАУНТ: ${account.name} (ID: ${account.id})")
                println("  Владелец: ${studio.customer.find { it.id == account.customerID }?.fullName}")
                println("  Баланс: ${account.balance}")
                println("  Песен: ${account.SongList.size}")
            }
        }
    }

    println("\n" + "=" * 50)
    println("🎵 Демонстрация завершена! 🎵")
}

operator fun String.times(n: Int): String = repeat(n)