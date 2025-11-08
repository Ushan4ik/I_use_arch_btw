package ru.tbank.education.school.lesson1

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

fun String.calculate(): Double? {
    val parts = this.split(" ")
    when (parts.size )
    {
        3->{val a = parts[0].toDoubleOrNull() ?: return null
            val operationSymbol = parts[1]
            val b = parts[2].toDoubleOrNull() ?:return null

            val operation = Operation3val.from(operationSymbol) ?: return null
            return operation.operationfun(a, b)}

    }
    return null
}

fun main() {
    val s = "2 / 0"
    println(s.calculate())
    val z = readln()
    println(z.calculate())

}

