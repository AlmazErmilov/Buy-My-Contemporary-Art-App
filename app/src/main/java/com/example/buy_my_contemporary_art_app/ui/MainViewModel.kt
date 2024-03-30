package com.example.buy_my_contemporary_art_app.ui

import com.example.buy_my_contemporary_art_app.Photo
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    // Example of a basket as a StateFlow list of Photo objects.
    private val _basket = MutableStateFlow<List<Photo>>(emptyList())
    val basket: StateFlow<List<Photo>> = _basket.asStateFlow()

    // Functions to interact with the basket
    fun addToBasket(photo: Photo) {
        _basket.value = _basket.value + photo
    }

    fun removeFromBasket(photo: Photo) {
        _basket.value = _basket.value - photo
    }
}