package com.example.buy_my_contemporary_art_app

import androidx.annotation.DrawableRes
import com.example.buy_my_contemporary_art_app.data.Artist

data class Photo(
    val id: Long,
    val title: String = "",
    @DrawableRes
    val imageResId: Int,
    val artist: Artist,
    val category: Category,
    var price: Float = 0.0f
)

enum class Category{
    ANIMALS,
    SPORTS,
    FOOD,
    ABSTRACT }
