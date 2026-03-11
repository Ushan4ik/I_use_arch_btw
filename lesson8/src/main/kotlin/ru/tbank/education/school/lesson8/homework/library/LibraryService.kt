package ru.tbank.education.school.lesson8.homework.library

class LibraryService {
    private val books = mutableMapOf<String, Book>()
    private val borrowedBooks = mutableSetOf<String>()
    private val borrowerFines = mutableMapOf<String, Int>()

    fun addBook(book: Book) {
        books[book.isbn] = book
    }

    fun borrowBook(isbn: String, borrower: String) {
        if (borrowedBooks.contains(isbn) or !books.containsKey(isbn)) {
            throw IllegalArgumentException("Книга не может быть взята")
        }
        borrowedBooks.add(isbn)
    }

    fun returnBook(isbn: String) {
        if (!borrowedBooks.contains(isbn)) {
            throw IllegalArgumentException("Книга не была выдана")
        }
        borrowedBooks.remove(isbn)
    }

    fun isAvailable(isbn: String): Boolean {
        return !borrowedBooks.contains(isbn)
    }

    fun calculateOverdueFine(isbn: String, daysOverdue: Int): Int {
        if (!borrowedBooks.contains(isbn) or (daysOverdue <= 10)) {
            return 0
        }
        return (daysOverdue-10) * 60
    }

    private fun hasOutstandingFines(borrower: String): Boolean {
        return (borrowerFines[borrower] ?: 0) > 0
    }
}