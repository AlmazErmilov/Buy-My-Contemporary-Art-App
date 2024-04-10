package com.example.buy_my_contemporary_art_app.ui


import androidx.lifecycle.ViewModel
import com.example.buy_my_contemporary_art_app.data.ShoppingCart
import com.example.buy_my_contemporary_art_app.data.ShoppingCartItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class ShoppingCartViewModel : ViewModel() {
    private val _cart = ShoppingCart()
    private val _cartItems = MutableStateFlow<List<ShoppingCartItem>>(_cart.items)
    val cartItems: StateFlow<List<ShoppingCartItem>> = _cartItems.asStateFlow()

    fun addItemToCart(item: ShoppingCartItem) {
        _cart.addItem(item) //.also { Log.d("ShoppingCart", "Item added: $item") }
        _cartItems.value = _cart.items //also { Log.d("ShoppingCart", "Cart items updated: $_cart.items") }
    }

    fun removeItemFromCart(item: ShoppingCartItem) {
        _cart.removeItem(item)
        _cartItems.value = _cart.items
    }
}