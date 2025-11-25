package com.example.hamcrest

import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

class LibraryTest {

    private val library = Library()
    private val book1 = Book("Harry Potter", "J.K. Rowling")
    private val book2 = Book("The Witcher", "A. Sapkowski")

    @Test
    fun testAddBook_ShouldAddBookToLibrary() {
        library.addBook(book1)

        // Vérifier que la collection contient l'objet Book
        assertThat(library.getAllBooks(), hasItem(book1))
    }

    @Test
    fun testFindBookByTitle_ShouldReturnBookWhenFound() {
        library.addBook(book1)

        val foundBook = library.findBookByTitle(book1.title)

        assertThat(foundBook, `is`(notNullValue()))

        assertThat(foundBook, `is`(equalTo(book1)))
    }

    @Test
    fun testFindBookByTitle_ShouldReturnNullWhenNotFound() {
        val foundBook = library.findBookByTitle("NonExistentBook")

        assertThat(foundBook, `is`(nullValue()))
    }

    @Test
    fun testBorrowBook_Available_ShouldReturnTrueAndMarkAsUnavailable() {
        library.addBook(book2)

        // L'emprunt devrait réussir
        assertThat(library.borrowBook(book2.title), `is`(true))

        // Le livre doit devenir indisponible
        assertThat(book2.isAvailable, `is`(false))
    }

    @Test
    fun testBorrowBook_AlreadyBorrowed_ShouldReturnFalse() {
        library.addBook(book1)
        book1.isAvailable = false // Marquer comme emprunté

        assertThat(library.borrowBook(book1.title), `is`(false))
    }

    @Test
    fun testBorrowBook_BookNotFound_ShouldReturnFalse() {
        assertThat(library.borrowBook("Unknown Title"), `is`(false))
    }
}
