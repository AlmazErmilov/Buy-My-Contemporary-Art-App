package com.example.buy_my_contemporary_art_app.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.buy_my_contemporary_art_app.data.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: ShoppingCartViewModel = viewModel()) {
    val cartItems = viewModel.cartItems.collectAsState().value

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
            ) {
                Button(onClick = { /* TODO: Handle artist click */ }) {
                    Text("Artist")
                }
                Button(onClick = { /* TODO: Handle category click */ }) {
                    Text("Category")
                }
            }
            ShoppingCart(cartItems, viewModel::removeItemFromCart)
        }
    }
}

@Composable
fun ShoppingCart(cartItems: List<ShoppingCartItem>, onDeleteClick: (ShoppingCartItem) -> Unit) {
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
                ShoppingCartItem(item, onDeleteClick)
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
fun ShoppingCartItem(item: ShoppingCartItem, onDeleteClick: (ShoppingCartItem) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Replace with Image when available
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Gray)
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
                onClick = { onDeleteClick(item) },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Delete")
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    // Provide a dummy ViewModel here if necessary for the preview to work
    HomeScreen()
}
