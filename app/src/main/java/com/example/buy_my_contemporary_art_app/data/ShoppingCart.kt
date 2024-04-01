package com.example.buy_my_contemporary_art_app.data

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
