package com.example.buy_my_contemporary_art_app.data

class DataSource {
    companion object {
        fun createDummyCartItems(): List<ShoppingCartItem> {
            return listOf(
                ShoppingCartItem(1, "Landscape Photo", "Wood Frame", 299.99f),
                ShoppingCartItem(2, "Abstract Art", "Metal Frame", 349.99f),
                ShoppingCartItem(3, "Portrait", "Plastic Frame", 199.99f),
                ShoppingCartItem(4, "Modern Art", "Wood Frame", 399.99f)
            )
        }
    }
}