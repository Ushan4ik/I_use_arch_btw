package HomeWork

fun main() {
    println("🎵 Демонстрация работы MusicStudio 🎵")
    println("=" * 50)

    val studio = MusicStudio()

    // 1. Создаем клиентов (артистов)
    println("\n1. СОЗДАНИЕ АРТИСТОВ:")
    studio.addClient("Eminem")
    studio.addClient("Dr. Dre")
    studio.addClient("Snoop Dogg")
    studio.addClient("Kendrick Lamar")

    // Попытка создать дубликат (должна быть ошибка)
    println("\nПопытка создать дубликат артиста:")
    studio.addClient("Eminem")

    // 2. Создаем аккаунты для артистов
    println("\n2. СОЗДАНИЕ АККАУНТОВ:")
    studio.addAccount("C-1", "Eminem Official")
    studio.addAccount("C-2", "Dr. Dre Productions")
    studio.addAccount("C-3", "Snoop Dogg Music")
    studio.addAccount("C-4", "Kendrick Lamar Studio")

    // Попытка создать аккаунт с существующим именем (должна быть ошибка)
    println("\nПопытка создать аккаунт с существующим именем:")
    studio.addAccount("C-1", "Eminem Official")

    // 3. Пополняем балансы
    println("\n3. ПОПОЛНЕНИЕ БАЛАНСОВ:")
    val eminemAccount = studio.accounts.find { it.name == "Eminem Official" }
    val dreAccount = studio.accounts.find { it.name == "Dr. Dre Productions" }
    val snoopAccount = studio.accounts.find { it.name == "Snoop Dogg Music" }

    eminemAccount?.deposit(5000.0)
    dreAccount?.deposit(10000.0)
    snoopAccount?.deposit(3000.0)

    println("Баланс Eminem: ${eminemAccount?.balance}")
    println("Баланс Dr. Dre: ${dreAccount?.balance}")
    println("Баланс Snoop Dogg: ${snoopAccount?.balance}")

    // 4. Создаем песни
    println("\n4. СОЗДАНИЕ ПЕСЕН:")
    studio.CreateSong("A-1", 1000.0, "Rap", "Lose Yourself", 326)
    studio.CreateSong("A-1", 800.0, "Rap", "The Real Slim Shady", 284)
    studio.CreateSong("A-2", 1500.0, "Rap", "Still D.R.E.", 304)

    // Попытка создать песню без денег
    println("\nПопытка создать песню без денег:")
    studio.CreateSong("A-3", 5000.0, "Rap", "Gin and Juice", 210)

    // 5. Создаем группу
    println("\n5. СОЗДАНИЕ ГРУППЫ:")
    val eminemAccId = eminemAccount?.id ?: "A-1"
    val dreAccId = dreAccount?.id ?: "A-2"
    val snoopAccId = snoopAccount?.id ?: "A-3"

    studio.createGroupAccount(
        "West Coast Legends",
        listOf(dreAccId, snoopAccId),
        "C-2" // Dr. Dre как создатель
    )

    // Попытка создать группу с существующим именем (должна быть ошибка)
    println("\nПопытка создать группу с существующим именем:")
    studio.createGroupAccount(
        "West Coast Legends",
        listOf(dreAccId, snoopAccId),
        "C-2"
    )

    // 6. Проверяем информацию
    println("\n6. ПРОВЕРКА ИНФОРМАЦИИ:")

    println("\nа) Информация об исполнителе:")
    studio.getInfoByName("Eminem")

    println("\nб) Информация о группе:")
    studio.getInfoByName("West Coast Legends")

    println("\nв) Информация об участнике группы:")
    studio.getInfoByName("Dr. Dre")

    println("\nг) Поиск несуществующего:")
    studio.getInfoByName("Non Existent Artist")

    // 7. Проверяем созданные данные
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

// Вспомогательная функция для повторения строки
operator fun String.times(n: Int): String = repeat(n)