package Kotlin

fun main(){
    val book1 = Book("The Lord of the Rings", "J.R.R. Tolkien", 1954)
    val book2 = Book("The Hobbit", "J.R.R. Tolkien", 1937)
    val book3 = Book("1984", "George Orwell", 1949)

    val library = Library()
    library.addBook(book1)
    library.addBook(book2)
    library.addBook(book3)

    val tolkienBooks = library.findByAuthor("J.R.R. Tolkien")
    println("Books by Tolkien:")
    tolkienBooks.forEach { println("    ${it.title}") }

    val bookByYear = library.findByYear(1949);
    println("Books from 1949:")
    bookByYear.forEach { println("    ${it.title}") }
}

data class Book(val title: String, val author: String, val year: Int)

class Library {
    private val books = mutableListOf<Book>()

    fun addBook(book: Book) {
        books.add(book)
    }

    fun findByAuthor(author: String): List<Book> {
        return books.filter { it.author == author }
    }

    fun findByYear(year: Int): List<Book> {
        return books.filter { it.year == year }
    }
}