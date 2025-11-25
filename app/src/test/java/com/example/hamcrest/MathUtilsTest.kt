package com.example.hamcrest

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertThrows
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers.*
import org.hamcrest.number.IsCloseTo.closeTo
import org.junit.jupiter.api.assertThrows

class MathUtilsTest {

    private val mathUtils = MathUtils()

    @Test
    fun testAdd_ShouldReturnCorrectSum() {
        assertThat(mathUtils.add(10, 5), `is`(equalTo(15)))
    }

    @Test
    fun testDivide_ValidValues_ShouldReturnCorrectQuotient() {
        assertThat(mathUtils.divide(10, 3), `is`(closeTo(3.333, 0.001)))
    }

    @Test
    fun testDivide_ByZero_ShouldThrowException() {
        assertThrows<IllegalArgumentException> {
            mathUtils.divide(10, 0)
        }
    }

    @Test
    fun testIsEven_WithEvenNumber_ShouldBeTrue() {
        assertThat(mathUtils.isEven(4), `is`(true))
    }

    @Test
    fun testIsEven_WithOddNumber_ShouldBeFalse() {
        assertThat(mathUtils.isEven(7), `is`(false))
    }
}
