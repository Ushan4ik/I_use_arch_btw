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
    COS(listOf("COS","cos","Cos","C","c"), { a-> cos(Math.toRadians(a))  });

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
            val operation = Operation2val.from(operationSymbol) ?: return null
            return operation.operationfun(a)}

    }
    return null
}

fun main() {
    val s = "2 / 0"
    println(s.calculate())
    val z = readln()
    println(z.calculate())

}

