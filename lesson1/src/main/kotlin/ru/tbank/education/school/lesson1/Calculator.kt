package ru.tbank.education.school.lesson1
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
enum class Operation3val(val symbols: List<String>, val operationfun: (Double, Double) -> Double?) {
    MULT(listOf("*", "multiply"), { a, b -> a * b }),
    PLUS(listOf("+", "plus"), { a, b -> a + b }),
    MINUS(listOf("-", "minus"), { a, b -> a - b }),
    DIVIDE(listOf("/", "d", "divide"),
        { a, b -> if(b!=0.0){a / b} else{   null }});
    companion object {
        fun from(symbol: String): Operation3val? {
            return entries.find { it.symbols.contains(symbol) }
        }
    }
}
enum class Operation2val(val symbols: List<String>, val operationfun: (Double) -> Double?) {
    SIN(listOf("SIN","sin","Sin","S","s"), { a-> sin(Math.toRadians(a))  }),
    COS(listOf("COS","cos","Cos","C","c"), { a-> cos(Math.toRadians(a))  }),
    SQRT(listOf("sqrt"), { a ->
        if (a < 0) null else sqrt(a)
    });

    companion object {
        fun from(symbol: String): Operation2val? {
            return entries.find { it.symbols.contains(symbol) }
        }
    }
}

fun String.calculate(): Double? {
    val parts = this.split(" ")
    when (parts.size )
    {
        3->{val a = parts[0].toDoubleOrNull() ?: return null
            val operationSymbol = parts[1]
            val b = parts[2].toDoubleOrNull() ?:return null

            val operation = Operation3val.from(operationSymbol) ?: return null
            return operation.operationfun(a, b)}
        2->{val a = parts[1].toDoubleOrNull() ?: return null
            val operationSymbol = parts[0]
            if(operationSymbol.toDoubleOrNull()!=null)
            {
                return a + operationSymbol.toDouble()
            }
            val operation = Operation2val.from(operationSymbol) ?: return null
            return operation.operationfun(a)}

    }
    return null
}

fun main() {
    val expressionsToTest = listOf(
        // Бинарные операции
        "2 + 3",
        "5 * 2",
        "10 / 2",
        "7 - 4",
        "10 / 0",        // Деление на ноль
        "5 plus 5",      // Альтернативный символ
        "invalid input", // Неверный формат

        // Унарные операции
        "sin 30",        // Синус 30 градусов
        "cos 60",        // Косинус 60 градусов
        "sqrt 9",        // Корень из 9
        "sqrt 2",        // Корень из 2
        "sqrt -4",       // Корень из отрицательного числа
        "SIN 90",        // Нечувствительность к регистру
        "sin invalid",   // Неверное число
        "hello 10",      // Неизвестная унарная операция
        "100"  ,
        "100 20"// Одиночное число (не поддерживается текущим парсером)
    )
    for (expr in expressionsToTest) {
        val result = expr.calculate()

        // Используем оператор Элвиса (?:) для предоставления значения по умолчанию, если result == null
        // А затем используем 'let' для выполнения действия над результатом, если он не null
        result?.let {
            // Если result не null, выводим его
            println("|$expr =  $it")
        } ?: run {
            // Если result был null, выводим сообщение об ошибке
            println("|$expr -> Error: ")
        }
    }
    val z = readln()
    println(z.calculate())


}

