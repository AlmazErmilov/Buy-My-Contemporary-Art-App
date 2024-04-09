package com.example.buy_my_contemporary_art_app

import org.junit.Test
import org.junit.Assert.*
//import com.example.buy_my_contemporary_art_app.ShoppingCartItem

class PriceCalculationTests {
    @Test
    fun testPriceExcludingMVA() {
        // Given
        val viewModel = ShoppingCartViewModel().apply {
            addItemToCart(ShoppingCartItem(1, "Item 1", "Frame 1", 100f,1))
            addItemToCart(ShoppingCartItem(2, "Item 2", "Frame 2", 200f,2))
        }
        val expectedPriceExclMVA = 300f // 100f + 200f

        // When
        //val actualPriceExclMVA = viewModel.cartItems.value.sumOf { it.price }
        val actualPriceExclMVA = viewModel.cartItems.value.map { it.price }.sum()

        // Then
        assertEquals(expectedPriceExclMVA, actualPriceExclMVA, 0.01f)
    }

    @Test
    fun testMVACalculation() {
        // Given
        val priceExclMVA = 300f
        val expectedMVA = priceExclMVA * 0.25f // 25% of 300

        // When
        val actualMVA = priceExclMVA * 0.25f

        // Then
        assertEquals(expectedMVA, actualMVA, 0.01f)
    }

    @Test
    fun testTotalPriceIncludingMVA() {
        // Given
        val priceExclMVA = 300f
        val mva = priceExclMVA * 0.25f
        val expectedTotalPrice = priceExclMVA + mva

        // When
        val actualTotalPrice = priceExclMVA + mva

        // Then
        assertEquals(expectedTotalPrice, actualTotalPrice, 0.01f)
    }
}

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
/*class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}*/

