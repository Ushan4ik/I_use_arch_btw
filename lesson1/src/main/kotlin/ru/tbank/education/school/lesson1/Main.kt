
data class Book(
    val title: String,
    val author: String,
    val year: Int,
    val genre: String
)

data class Person(
    val name: String,
    val age: Int
)

class Library {
    // Список всех книг в библиотеке
    private val books = mutableListOf<Book>()
    // Список всех зарегистрированных посетителей
    private val people = mutableListOf<Person>()
    // Карта для отслеживания взятых книг: ключ - книга, значение - человек, который ее взял
    private val takenBooks = mutableMapOf<Book, Person>()

    /**
     * Добавляет книгу в библиотеку.
     * @param book Объект книги для добавления.
     */
    fun addBook(book: Book) {
        // todo: добавить книгу в список books
        books.add(book)
    }

    /**
     * Добавляет человека в список посетителей библиотеки.
     * @param person Объект человека для добавления.
     */
    fun addPerson(person: Person) {
        // todo: добавить человека в список people
        people.add(person)
    }

    /**
     * Возвращает список всех книг, которые в данный момент доступны
     * (то есть не взяты ни одним читателем).
     * @return Список доступных книг.
     */
    fun getAvailableBooks(): List<Book> {
        // todo: вернуть только те книги, которые НЕ находятся в takenBooks
        // Фильтруем все книги, оставляя только те, которые не являются ключом в takenBooks
        return books.filter { !takenBooks.containsKey(it) }
    }

    /**
     * Возвращает список книг определённого автора.
     * Сравнение имени автора производится без учета регистра.
     * @param author Имя автора для поиска.
     * @return Список книг указанного автора.
     */
    fun getBooksByAuthor(author: String): List<Book> {
        // todo: вернуть книги, у которых автор совпадает с переданным (игнорируя регистр)
        // Используем equals с ignoreCase = true для сравнения автора
        return books.filter { it.author.equals(author, ignoreCase = true) }
    }

    /**
     * Возвращает список книг определённого жанра.
     * Сравнение жанра производится без учета регистра.
     * @param genre Жанр для поиска.
     * @return Список книг указанного жанра.
     */
    fun getBooksByGenre(genre: String): List<Book> {
        // todo: вернуть книги, у которых жанр совпадает с переданным (игнорируя регистр)
        // Используем equals с ignoreCase = true для сравнения жанра
        return books.filter { it.genre.equals(genre, ignoreCase = true) }
    }

    /**
     * Человек пытается взять книгу по названию.
     * @param personName Имя человека, который пытается взять книгу.
     * @param bookTitle Название книги, которую пытаются взять.
     * @return true, если книга успешно взята; false в противном случае (например, человек не найден,
     *         книга не найдена, или книга уже взята).
     */
    fun takeBook(personName: String, bookTitle: String): Boolean {
        // todo:
        // 1. Найти человека по имени (без учета регистра)
        val person = people.find { it.name.equals(personName, ignoreCase = true) }
        if (person == null) {
            println("Ошибка: Человек с именем '$personName' не найден в списке посетителей.")
            return false
        }

        // 2. Найти книгу по названию (без учета регистра)
        val book = books.find { it.title.equals(bookTitle, ignoreCase = true) }
        if (book == null) {
            println("Ошибка: Книга с названием '$bookTitle' не найдена в библиотеке.")
            return false
        }

        // 3. Проверить, что книга существует и доступна (её нет в takenBooks)
        if (takenBooks.containsKey(book)) {
            println("Ошибка: Книга '${book.title}' уже взята другим читателем.")
            return false
        }

        // 4. Если всё в порядке — добавить запись в takenBooks и вернуть true
        takenBooks[book] = person
        println("Успех: '${book.title}' взята читателем ${person.name}.")
        return true
    }

    /**
     * Возвращает список всех зарегистрированных посетителей библиотеки.
     * Возвращается копия списка, чтобы предотвратить прямое изменение внутреннего состояния.
     * @return Список всех посетителей.
     */
    fun getAllPeople(): List<Person> {
        // todo: вернуть копию списка people
        // Возвращаем копию списка, чтобы предотвратить внешнее изменение внутреннего состояния библиотеки
        return people.toList()
    }

    /**
     * Возвращает список книг, которые взял указанный человек.
     * Сравнение имени человека производится без учета регистра.
     * @param personName Имя человека.
     * @return Список книг, взятых этим человеком. Возвращает пустой список, если человек не найден
     *         или ничего не брал.
     */
    fun getBooksTakenByPerson(personName: String): List<Book> {
        // todo: вернуть список книг, которые взял человек с указанным именем
        // 1. Находим человека по имени (case-insensitive)
        val targetPerson = people.find { it.name.equals(personName, ignoreCase = true) }

        // 2. Если человек не найден, возвращаем пустой список
        if (targetPerson == null) {
            return emptyList()
        }

        // 3. Фильтруем takenBooks по значению (Person) и возвращаем ключи (Book)
        return takenBooks.filterValues { it == targetPerson }.keys.toList()
    }

    /**
     * Возвращает информацию о том, кто взял конкретную книгу.
     * Сравнение названия книги производится без учета регистра.
     * @param bookTitle Название книги.
     * @return Объект Person, который взял книгу, или null, если книга не найдена
     *         или не взята никем.
     */
    fun getPersonWhoTookBook(bookTitle: String): Person? {
        // todo: найти книгу по названию и вернуть человека, который её взял (или null)
        // 1. Находим книгу по названию (case-insensitive)
        val targetBook = books.find { it.title.equals(bookTitle, ignoreCase = true) }

        // 2. Если книга не найдена в общем списке библиотеки, возвращаем null
        if (targetBook == null) {
            return null
        }

        // 3. Возвращаем Person, связанный с этой книгой в takenBooks, или null, если книга не взята
        return takenBooks[targetBook]
    }
}

//Пример использования:
fun main() {
    val library = Library()

    // Добавляем книги
    library.addBook(Book("Война и мир", "Лев Толстой", 1869, "Роман"))
    library.addBook(Book("Преступление и наказание", "Фёдор Достоевский", 1866, "Роман"))
    library.addBook(Book("Мастер и Маргарита", "Михаил Булгаков", 1967, "Фантастика"))
    library.addBook(Book("Идиот", "Фёдор Достоевский", 1869, "Роман"))
    library.addBook(Book("Отцы и дети", "Иван Тургенев", 1862, "Роман"))


    // Добавляем людей
    library.addPerson(Person("Анна", 25))
    library.addPerson(Person("Иван", 30))
    library.addPerson(Person("Петр", 40))

    println("--- Начальное состояние библиотеки ---")
    // Проверяем доступные книги
    println("Все книги в библиотеке: ${library.getAllBooks().map { it.title }}") // Вспомогательный метод, который можно добавить, или использовать getAvailableBooks
    println("Доступные книги: ${library.getAvailableBooks().map { it.title }}") // Ожидаем все 5 книг

    // Книги по автору (case-insensitive)
    println("Книги Федора Достоевского: ${library.getBooksByAuthor("Федор Достоевский").map { it.title }}") // Ожидаем "Преступление и наказание", "Идиот"
    println("Книги Льва Толстого: ${library.getBooksByAuthor("лев толстой").map { it.title }}") // Ожидаем "Война и мир"

    // Книги по жанру (case-insensitive)
    println("Книги в жанре 'Роман': ${library.getBooksByGenre("роман").map { it.title }}") // Ожидаем "Война и мир", "Преступление и наказание", "Идиот", "Отцы и дети"
    println("Книги в жанре 'Фантастика': ${library.getBooksByGenre("Фантастика").map { it.title }}") // Ожидаем "Мастер и Маргарита"

    println("\n--- Операции по взятию книг ---")
    // Берём книгу "Мастер и Маргарита" для Анны
    library.takeBook("Анна", "Мастер и Маргарита") // Ожидаем Успех
    // Берём книгу "Война и мир" для Ивана
    library.takeBook("Иван", "Война и мир") // Ожидаем Успех
    // Берём книгу "Идиот" для Петра
    library.takeBook("Петр", "Идиот") // Ожидаем Успех


    println("\n--- Проверка ошибок и повторного взятия ---")
    // Пытаемся взять ту же книгу снова
    library.takeBook("Иван", "Мастер и Маргарита") // Ожидаем Ошибка: Книга уже взята.
    // Пытаемся взять несуществующую книгу
    library.takeBook("Петр", "Несуществующая книга") // Ожидаем Ошибка: Книга не найдена.
    // Пытаемся взять книгу несуществующим человеком
    library.takeBook("Ольга", "Отцы и дети") // Ожидаем Ошибка: Человек не найден.


    println("\n--- Проверка состояния после взятия ---")
    // Проверяем доступные книги после взятия
    println("Доступные книги: ${library.getAvailableBooks().map { it.title }}") // Ожидаем "Преступление и наказание", "Отцы и дети"

    // Проверяем, кто взял книгу "Мастер и Маргарита"
    val personWhoTookMaster = library.getPersonWhoTookBook("Мастер и Маргарита")
    println("Книгу 'Мастер и Маргарита' взял: ${personWhoTookMaster?.name ?: "Никто"}") // Ожидаем "Анна"

    // Проверяем, кто взял книгу "Война и мир"
    val personWhoTookWar = library.getPersonWhoTookBook("Война и мир")
    println("Книгу 'Война и мир' взял: ${personWhoTookWar?.name ?: "Никто"}") // Ожидаем "Иван"

    // Проверяем, кто взял недоступную книгу (ту, которая еще в библиотеке)
    val personWhoTookAvailable = library.getPersonWhoTookBook("Преступление и наказание")
    println("Книгу 'Преступление и наказание' взял: ${personWhoTookAvailable?.name ?: "Никто"}") // Ожидаем "Никто"

    // Проверяем, какие книги взял человек Анна
    println("Анна взяла: ${library.getBooksTakenByPerson("Анна").map { it.title }}") // Ожидаем "Мастер и Маргарита"

    // Проверяем, какие книги взял человек Иван
    println("Иван взял: ${library.getBooksTakenByPerson("Иван").map { it.title }}") // Ожидаем "Война и мир"

    // Проверяем, какие книги взял человек Петр
    println("Петр взял: ${library.getBooksTakenByPerson("Петр").map { it.title }}") // Ожидаем "Идиот"

    // Проверяем, какие книги взял человек, который ничего не брал
    library.addPerson(Person("Марина", 22))
    println("Марина взяла: ${library.getBooksTakenByPerson("Марина").map { it.title }}") // Ожидаем пустой список

    // Проверяем список всех посетителей
    println("Все посетители: ${library.getAllPeople().map { it.name }}") // Ожидаем "Анна", "Иван", "Петр", "Марина"
}

// Дополнительный вспомогательный метод (можно добавить в класс Library для полноты)
fun Library.getAllBooks(): List<Book> {
    // В классе Library у вас есть доступ к private val books.
    // Здесь мы просто возвращаем копию списка всех книг.
    // Если бы это было в Library, было бы return books.toList()
    // Так как это extension function, предполагаем, что у вас есть доступ
    // к this.books (которое на самом деле private, но для примера сойдет)
    // В реальном коде, лучше добавить эту функцию непосредственно в класс Library.
    return this.getAvailableBooks() + this.getTakenBooks() // Можно собрать из доступных и взятых
}

fun Library.getTakenBooks(): List<Book> {
    // Для примера, как можно получить все взятые книги
    return this.javaClass.getDeclaredField("takenBooks").apply { isAccessible = true }.get(this)
        .let { it as Map<Book, Person> }.keys.toList()
}

рфы