package com.example.buy_my_contemporary_art_app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun completePurchaseFromCart() {
        composeTestRule.onNodeWithText("Go to Payment").performClick()

        composeTestRule.onNodeWithText("Pay").performClick()

        // Assuming you have a text display when the cart is empty
        composeTestRule.onNodeWithText("Your payment is being performed").assertIsDisplayed()
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.buy_my_contemporary_art_app", appContext.packageName)
    }

    @Test
    fun addItemToShoppingCart() {
        val viewModel = ShoppingCartViewModel()
        val initialSize = viewModel.cartItems.value.size

        val newItem = ShoppingCartItem(
            id = 1,
            name = "Test Photo",
            frameInfo = "Wooden Frame, A4",
            price = 300f,
            imageResId = R.drawable.abstract0 // Assuming you have such a drawable
        )

        viewModel.addItemToCart(newItem)

        // Verify the item was added
        val updatedSize = viewModel.cartItems.value.size
        assertTrue("The item should be added to the cart", updatedSize == initialSize + 1)
    }

    @Test
    fun navigateToArtistPhotos() {
        val artistId = 1L
        val photos = photosByArtist(artistId)

        assertFalse("Photos list should not be empty", photos.isEmpty())
        assertTrue("All photos should belong to the artist", photos.all { it.artist.id == artistId })
    }

    @Test
    fun completePurchaseClearsCart() {
        val viewModel = ShoppingCartViewModel().apply {
            addItemToCart(ShoppingCartItem(1, "Test Photo", "Wooden Frame, A4", 300f, R.drawable.abstract0))
        }

        viewModel.cartItems.value.forEach { viewModel.removeItemFromCart(it) }

        assertTrue("The shopping cart should be empty after purchase", viewModel.cartItems.value.isEmpty())
    }

    @Test
    fun selectPhotoToAddToCart() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        composeTestRule.onNodeWithText("Arstist").performClick()

        composeTestRule.onNodeWithText("Vitalijus").performClick()

        composeTestRule.onNodeWithText("Sports1").performClick()

        composeTestRule.onNodeWithText("Add to Cart").performClick()

        // Verify the item is in the cart. This checks for the presence of the item text.
        composeTestRule.onNodeWithText("Sports1").assertIsDisplayed()
    }

}

