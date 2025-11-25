package com.example.hamcrest

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers.*
import org.hamcrest.number.IsCloseTo.closeTo
import org.hamcrest.Matchers.hasItem
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.api.assertThrows

class ProductManagerTest {

    private lateinit var manager: ProductManager
    private val productA = Product("Laptop", 1200.0)
    private val productB = Product("Mouse", 25.50)

    @BeforeEach
    fun setup() {
        manager = ProductManager()
    }

    @Test
    fun testAddProduct_ShouldAddValidProduct() {
        manager.addProduct(productA.name, productA.price)

        assertThat(manager.getProducts(), hasItem(productA))
        assertThat(manager.getProducts(), hasSize(1))
    }

    @Test
    fun testAddProduct_NegativePrice_ShouldThrowException() {
        assertThrows<IllegalArgumentException> {
            manager.addProduct("DefectiveItem", -10.0)
        }
    }

    @Test
    fun testRemoveProduct_ShouldRemoveExistingProduct() {
        manager.addProduct(productA.name, productA.price)
        manager.addProduct(productB.name, productB.price)

        manager.removeProduct(productA.name)

        assertThat(manager.getProducts(), not(hasItem(productA)))
    }

    @Test
    fun testGetProductPrice_ShouldReturnPrice() {
        manager.addProduct(productA.name, productA.price)

        val actualPrice = manager.getProductPrice(productA.name)

        assertThat(actualPrice, `is`(notNullValue()))
        assertThat(actualPrice, `is`(equalTo(1200.0)))
    }

    @Test
    fun testCalculateTotalPrice_ShouldReturnCorrectSum() {
        manager.addProduct("P1", 10.0)
        manager.addProduct("P2", 5.50)

        val actualTotal = manager.calculateTotalPrice()

        assertThat(actualTotal, `is`(closeTo(15.50, 0.001)))
    }
}
