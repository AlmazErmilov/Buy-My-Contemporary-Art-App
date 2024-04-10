package com.example.buy_my_contemporary_art_app.data
import androidx.annotation.DrawableRes


data class ShoppingCartItem(
    val id: Int,
    val name: String,
    val frameInfo: String,
    val price: Float,
    @DrawableRes val imageResId: Int
)

class ShoppingCart {
    private val _items = mutableListOf<ShoppingCartItem>()
    val items: List<ShoppingCartItem> get() = _items.toList()

    fun addItem(item: ShoppingCartItem) {
        _items.add(item)
    }

    fun removeItem(item: ShoppingCartItem) {
        _items.remove(item)
    }
}
