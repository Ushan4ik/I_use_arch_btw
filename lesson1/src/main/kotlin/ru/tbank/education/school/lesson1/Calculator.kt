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
    // Сначала пробуем разобрать как выражение с пробелами
    val partsWithSpaces = this.split(" ")
    when (partsWithSpaces.size) {
        3 -> {
            val a = partsWithSpaces[0].toDoubleOrNull() ?: return null
            val operationSymbol = partsWithSpaces[1]
            val b = partsWithSpaces[2].toDoubleOrNull() ?: return null

            val operation = Operation3val.from(operationSymbol) ?: return null
            return operation.operationfun(a, b)
        }
        2 -> {
            val a = partsWithSpaces[1].toDoubleOrNull() ?: return null
            val operationSymbol = partsWithSpaces[0]
            if(operationSymbol.toDoubleOrNull()!=null) {
                return a + operationSymbol.toDouble()
            }
            val operation = Operation2val.from(operationSymbol) ?: return null
            return operation.operationfun(a)
        }
    }

    // Если не получилось с пробелами, пробуем разобрать без пробелов
    return tryParseWithoutSpaces(this)
}

private fun tryParseWithoutSpaces(expr: String): Double? {
    // Пробуем найти бинарные операции
    for (op in Operation3val.entries) {
        for (symbol in op.symbols) {
            if (expr.contains(symbol) && symbol.length > 0) {
                val parts = expr.split(symbol)
                if (parts.size == 2) {
                    val a = parts[0].toDoubleOrNull() ?: continue
                    val b = parts[1].toDoubleOrNull() ?: continue
                    return op.operationfun(a, b)
                }
            }
        }
    }

    // Пробуем найти унарные операции
    for (op in Operation2val.entries) {
        for (symbol in op.symbols) {
            if (expr.startsWith(symbol) && expr.length > symbol.length) {
                val numberStr = expr.substring(symbol.length)
                val a = numberStr.toDoubleOrNull() ?: continue
                return op.operationfun(a)
            }
            if (expr.endsWith(symbol) && expr.length > symbol.length) {
                val numberStr = expr.substring(0, expr.length - symbol.length)
                val a = numberStr.toDoubleOrNull() ?: continue
                // Для некоторых операций порядок может иметь значение
                // Например, "30sin" может быть не так очевидно как "sin30"
                // Но для простоты поддерживаем оба варианта
                return op.operationfun(a)
            }
        }
    }

    return null
}

fun main() {
    val expressionsToTest = listOf(
        "2 + 3",
        "5 * 2",
        "10 / 2",
        "7 - 4",
        "10 / 0",
        "5 plus 5",
        "fasdx input",
        "sin 30",
        "cos 60",
        "sqrt 9",
        "sqrt 2",
        "sqrt -4",
        "SIN 90",
        "sin invalid",
        "hello 10",
        "100",
        "100 20",
        // Тесты без пробелов
        "2+3",
        "5*2",
        "10/2",
        "7-4",
        "sin30",
        "cos60",
        "sqrt9",
        "SIN90",
        "5plus5",
        "30sin", // обратный порядок
        "60cos"  // обратный порядок
    )
    for (expr in expressionsToTest) {
        val result = expr.calculate()

        result?.let {
            println("|$expr =  $it")
        } ?: run{
            println("|$expr -> Error: ")
        }
    }
    while (true) {
        println("-------------------------")
        println("to quit write  'q' ")
        println("-------------------------")
        println("Напишите ваше Выражение (можно без пробелов):")
        val z = readln()
        if(z=="q")break;
        val result   = z.calculate()
        result?.let {
            println("$z =  $it")
        } ?: run{
            println("$z -> Error: ")
        }
    }
}