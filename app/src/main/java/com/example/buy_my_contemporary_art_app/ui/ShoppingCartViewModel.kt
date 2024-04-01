package com.example.buy_my_contemporary_art_app.ui

import androidx.lifecycle.ViewModel
import com.example.buy_my_contemporary_art_app.data.ShoppingCartItem
import com.example.buy_my_contemporary_art_app.data.ShoppingCart
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ShoppingCartViewModel : ViewModel() {
    private val _cart = ShoppingCart()
    private val _cartItems = MutableStateFlow<List<ShoppingCartItem>>(_cart.items)
    val cartItems: StateFlow<List<ShoppingCartItem>> = _cartItems.asStateFlow()

    fun addItemToCart(item: ShoppingCartItem) {
        _cart.addItem(item)
        //_cartItems.value = _cart.items
        _cartItems.update { _cart.items }
    }

    fun removeItemFromCart(item: ShoppingCartItem) {
        _cart.removeItem(item)
        //_cartItems.value = _cart.items
        _cartItems.update { _cart.items }
    }
}