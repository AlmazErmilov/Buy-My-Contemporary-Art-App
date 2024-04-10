package com.example.buy_my_contemporary_art_app.data

import android.content.Context
import androidx.annotation.DrawableRes
import com.example.buy_my_contemporary_art_app.R

data class Artist(
    val id: Long,
    val name: String,
    @DrawableRes val imageResId: Int
)

object ShoppingCartDataSource {
    val dummyItems = listOf(
        ShoppingCartItem(1, "Landscape Painting", "Wooden Frame", 150f, 0),
        //ShoppingCartItem(2, "Abstract Artwork", "Metal Frame", 200f),
        //ShoppingCartItem(3, "Portrait", "Plastic Frame", 100f),
        //ShoppingCartItem(4, "Modern Art", "Metal Frame", 300f)
    )
}

class DataSourceArtist(context: Context){
    val artists = listOf(
        Artist(0, context.getString(R.string.billy), R.drawable.billyherr),
        Artist(1, context.getString(R.string.anthony), R.drawable.vit),
        Artist(2, context.getString(R.string.tj), R.drawable.cummings),
        Artist(3, context.getString(R.string.danny), R.drawable.danny),
    )
}

object DataSourcePhotos {
    val allPhotos = listOf(
        Photo(id = 1, title = "Food0", imageResId = R.drawable.food0,
            artist = Artist(0, R.string.danny.toString(), R.drawable.food0),
            category = Category.FOOD, price = 500f),
        Photo(id = 2, title = "Food1", imageResId = R.drawable.food1,
            artist = Artist(1, R.string.anthony.toString(), R.drawable.food0),
            category = Category.FOOD, price = 300f),
        Photo(id = 3, title = "Food2", imageResId = R.drawable.food2,
            artist = Artist(2, R.string.tj.toString(), R.drawable.food0),
            category = Category.FOOD, price = 300f),
        Photo(id = 4, title = "Food3", imageResId = R.drawable.food3,
            artist = Artist(3, "Danny Lee", R.drawable.food0),
            category = Category.FOOD, price = 300f),

        Photo(id = 5, title = "Abstract0", imageResId = R.drawable.abstract0,
            artist = Artist(0, R.string.danny.toString(), R.drawable.food0),
            category = Category.ABSTRACT, price = 300f),
        Photo(id = 6, title = "Abstract1", imageResId = R.drawable.abstract1,
            artist = Artist(1, R.string.anthony.toString(), R.drawable.food0),
            category = Category.ABSTRACT, price = 300f),
        Photo(id = 7, title = "Abstract2", imageResId = R.drawable.abstract2,
            artist = Artist(2, R.string.tj.toString(), R.drawable.food0),
            category = Category.ABSTRACT, price = 300f),
        Photo(id = 8, title = "Abstract3", imageResId = R.drawable.abstract3,
            artist = Artist(3, R.string.billy.toString(), R.drawable.food0),
            category = Category.ABSTRACT, price = 300f),

        Photo(id = 9, title = "Animal0", imageResId = R.drawable.animal0,
            artist = Artist(0, R.string.danny.toString(), R.drawable.food0),
            category = Category.ANIMALS, price = 300f),
        Photo(id = 10, title = "Animal1", imageResId = R.drawable.animal1,
            artist = Artist(1, R.string.anthony.toString(), R.drawable.food0),
            category = Category.ANIMALS, price = 300f),
        Photo(id = 11, title = "Animal2", imageResId = R.drawable.animal2,
            artist = Artist(2, R.string.tj.toString(), R.drawable.food0),
            category = Category.ANIMALS, price = 300f),
        Photo(id = 12, title = "Animal3", imageResId = R.drawable.animal3,
            artist = Artist(3, R.string.billy.toString(), R.drawable.food0),
            category = Category.ANIMALS, price = 300f),

        Photo(id = 13, title = "Sports0", imageResId = R.drawable.sports0,
            artist = Artist(0, R.string.danny.toString(), R.drawable.food0),
            category = Category.SPORTS, price = 300f),
        Photo(id = 14, title = "Sports1", imageResId = R.drawable.sports1,
            artist = Artist(1, R.string.anthony.toString(), R.drawable.food0),
            category = Category.SPORTS, price = 300f),
        Photo(id = 15, title = "Sports2", imageResId = R.drawable.sports2,
            artist = Artist(2, R.string.tj.toString(), R.drawable.food0),
            category = Category.SPORTS, price = 300f),
        Photo(id = 16, title = "Sports3", imageResId = R.drawable.sports3,
            artist = Artist(3, R.string.billy.toString(), R.drawable.food0),
            category = Category.SPORTS, price = 300f),
    )
}