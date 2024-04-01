package com.example.buy_my_contemporary_art_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.buy_my_contemporary_art_app.ui.HomeScreen
import com.example.buy_my_contemporary_art_app.ui.ShoppingCartViewModel
import com.example.buy_my_contemporary_art_app.ui.theme.BuyMyContemporaryArtAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shoppingCartViewModel by viewModels<ShoppingCartViewModel>()

        setContent {
            BuyMyContemporaryArtAppTheme {
                HomeScreen(shoppingCartViewModel)
            }
        }
    }
}
