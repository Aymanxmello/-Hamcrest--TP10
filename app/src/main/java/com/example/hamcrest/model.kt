package com.example.hamcrest

// Fichier : src/main/kotlin/model.kt

// -------------------------------------------------------------------
// EXERCICE 1: MathUtils
// -------------------------------------------------------------------
class MathUtils {
    fun add(a: Int, b: Int): Int = a + b

    fun subtract(a: Int, b: Int): Int = a - b

    fun divide(a: Int, b: Int): Double {
        if (b == 0) {
            throw IllegalArgumentException("Le diviseur ne peut pas être zéro.")
        }
        return a.toDouble() / b.toDouble()
    }

    fun isEven(n: Int): Boolean = n % 2 == 0
}

// -------------------------------------------------------------------
// EXERCICE 2: validerMotDePasse (Fonction de validation)
// -------------------------------------------------------------------
fun validerMotDePasse(password: String): Boolean {
    // Règle 1: Au moins 8 caractères
    if (password.length < 8) return false

    // Règle 2: Au moins une lettre majuscule
    if (!password.any { it.isUpperCase() }) return false

    // Règle 3: Au moins un chiffre
    if (!password.any { it.isDigit() }) return false

    return true
}

// -------------------------------------------------------------------
// EXERCICE 3: ProductManager
// -------------------------------------------------------------------
data class Product(val name: String, val price: Double)

class ProductManager {
    private val products = mutableListOf<Product>()

    fun addProduct(name: String, price: Double) {
        if (price <= 0) {
            throw IllegalArgumentException("Le prix doit être positif.")
        }
        products.add(Product(name, price))
    }

    fun removeProduct(name: String) {
        products.removeAll { it.name == name }
    }

    fun getProductPrice(name: String): Double? {
        return products.find { it.name == name }?.price
    }

    fun calculateTotalPrice(): Double {
        return products.sumOf { it.price }
    }

    fun getProducts(): List<Product> = products.toList()
}

// -------------------------------------------------------------------
// EXERCICE 4: Library
// -------------------------------------------------------------------
data class Book(
    val title: String,
    val author: String,
    var isAvailable: Boolean = true
)

class Library {
    private val books = mutableListOf<Book>()

    fun addBook(book: Book) {
        books.add(book)
    }

    fun findBookByTitle(title: String): Book? {
        return books.find { it.title == title }
    }

    fun borrowBook(title: String): Boolean {
        val book = findBookByTitle(title)

        if (book == null || !book.isAvailable) {
            return false // Livre introuvable ou déjà emprunté
        }

        book.isAvailable = false
        return true // Emprunt réussi
    }

    fun getAllBooks(): List<Book> = books.toList()
}