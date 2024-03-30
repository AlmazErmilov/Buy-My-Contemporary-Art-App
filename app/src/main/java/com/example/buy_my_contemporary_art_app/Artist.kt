package com.example.buy_my_contemporary_art_app

import android.content.Context
import com.example.buy_my_contemporary_art_app.R

data class Artist(
    val id: Long,
    val name: String
)

class DataSourceArtist(context: Context){
    val artists = listOf(
        Artist(0, context.getString(R.string.billy)),
        Artist(1, context.getString(R.string.anthony)),
        Artist(2, context.getString(R.string.tj)),
        Artist(3, context.getString(R.string.danny)),
    )
}