package ru.tbank.education.school.lesson1

enum class Operation(val symbols: List<String>, val operationfun: (Double, Double) -> Double) {
    MULT(listOf("*", "multiply"), { a, b -> a * b }),
    PLUS(listOf("+", "plus"), { a, b -> a + b }),
    MINUS(listOf("-", "minus"), { a, b -> a - b }),
    DIVIDE(listOf("/", "d", "divide"), { a, b -> a / b });

    companion object {
        fun from(symbol: String): Operation? {
            return entries.find { it.symbols.contains(symbol) }
        }
    }
}

fun String.calculate(): Double? {
    val parts = this.split(" ")
    if (parts.size != 3) {
        return null
    }

    val a = parts[0].toDoubleOrNull() ?: return null
    val operationSymbol = parts[1]
    val b = parts[2].toDoubleOrNull() ?: return null

    val operation = Operation.from(operationSymbol) ?: return null

    return operation.operationfun(a, b)
}

fun main() {
    val s = "2 + 3"
    println(s.calculate())
    val z = readln()
    println(z.calculate())

}

