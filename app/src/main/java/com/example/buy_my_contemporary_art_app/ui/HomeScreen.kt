package com.example.buy_my_contemporary_art_app.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.tooling.preview.Preview

// Dummy data class for shopping cart items
data class CartItem(
    val id: Int,
    val name: String,
    val frameInfo: String,
    val price: Float
)

// Temporary list of cart items, upgrade needed, new class
val cartItems = listOf(
    CartItem(0 , "Picture 1", "Metal frame", 146.00f),
    CartItem(1, "Picture 2", "Wooden frame", 400.00f)
)

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold() {
        Column(modifier = Modifier.fillMaxSize()) {
            CenterAlignedTopAppBar(
                title = { Text("The Art Dealer") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {// upgrade needed
                Button(onClick = { /* TODO: Handle artist click */ }) {
                    Text("Artist")
                }
                Button(onClick = { /* TODO: Handle category click */ }) {
                    Text("Category")
                }
            }
            ShoppingCart(cartItems)
        }
    }
}

@Composable
fun ShoppingCart(cartItems: List<CartItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            "My shopping cart",
            style = MaterialTheme.typography.displaySmall
        )
        Divider(color = Color.LightGray, thickness = 1.dp)
        LazyColumn {
            items(cartItems) { item ->
                ShoppingCartItem(item)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /* TODO: Navigate to payment */ },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Go to payment")
            }
        }
    }
}

@Composable
fun ShoppingCartItem(item: CartItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // should be the image but for simplicity, we are using a placeholder
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Gray)  // upgrade, actual photo
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text("Name: ${item.name}")
                Text("Frame info: ${item.frameInfo}")
                Text("Price incl. frame: NOK ${item.price}")
            }
            Button(
                onClick = { /* TODO: Remove item from cart */ }, // upgrade needed
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Delete")
            }
        }
    }
}
