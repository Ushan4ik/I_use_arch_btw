class StudentProfile(
    var name: String,
    var birthYear: Int,
    var className: String
) {
    fun calculateAge(currentYear: Int): Int {
        return currentYear - birthYear
    }

    fun printStudentInfo(currentYear: Int) {
        println("Имя: $name")
        println("Класс: $className")
        println("Возраст: ${calculateAge(currentYear)}")
    }

    fun saveToFile(filename: String) {
        // сохраняем данные в файл
    }
}
