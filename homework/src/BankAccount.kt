    package homework


    /**
     *
     * В данном классе `BankAccount` реализован метод `transfer()` для перевода денег
     * с одного счета на другой. Однако в текущей реализации присутствует
     * **дедлок (deadlock)** - ситуация взаимной блокировки потоков.
     *
     * Проблема:
     * Дедлок возникает при одновременном выполнении двух переводов в противоположных направлениях:
     *
     * Задание:
     * 1. Проанализируйте код и воспроизведите дедлок с помощью тестов
     * 2. Исправьте метод `transfer()` так, чтобы дедлок был невозможен
     * 3. Убедитесь, что все тесты проходят
     */
    fun main(){

        val bankAccount1 = BankAccount("a",100000000)
        val bankAccount2 = BankAccount("b",100000000)
        bankAccount2.transfer(bankAccount1,100)
        println(bankAccount1.balance)
        (0..30).forEach {
            println(""" 
                it has been started
                !!!!!!!!!!!!!!!!!!!!
                !!!!!!!!!!!!!!!!!!!
            """.trimIndent())
            val thread1 = Thread {
                bankAccount2.transfer(bankAccount1, 100)
            }
            val thread2 = Thread {
                bankAccount1.transfer(bankAccount2, 100)
            }
            thread1.start()
            thread2.start()
        }
        println(bankAccount1.balance)
        println(bankAccount2.balance)

    }
    class BankAccount(val id: String, var balance: Int) {

        fun transfer(to: BankAccount, amount: Int) {
            synchronized(this) {
                Thread.sleep(10)

                synchronized(to) {
                    if (balance >= amount) {
                        balance -= amount
                        to.balance += amount
                    }
                }
            }
        }
    }