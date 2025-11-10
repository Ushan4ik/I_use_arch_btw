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
        "100 20"
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
        println("Напишите ваше Выражение строго разделяя пробелами:")
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