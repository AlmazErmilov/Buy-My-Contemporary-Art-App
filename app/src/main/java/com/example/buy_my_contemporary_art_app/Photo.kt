package com.example.buy_my_contemporary_art_app

data class Photo(
    val id: Long,
    val title: String,
    val imageResId: Int,
    val artist: Artist,
    val category: Category,
    var price: Float
)

enum class Category {
    ANIMALS, SPORTS, FOOD, ABSTRACT
}