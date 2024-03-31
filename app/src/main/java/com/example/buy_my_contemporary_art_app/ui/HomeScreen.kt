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
import androidx.compose.foundation.clickable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.*
import com.example.buy_my_contemporary_art_app.data.ShoppingCartDataSource
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.foundation.rememberScrollState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: ShoppingCartViewModel = viewModel()) {
    val cartItems = viewModel.cartItems.collectAsState().value
    val scrollState = rememberScrollState()

    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                //.verticalScroll(scrollState)
        ) {
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
            DummyItemButtons(viewModel)
            ShoppingCart(cartItems, viewModel::removeItemFromCart)
        }
    }
}

@Composable
fun DummyItemButtons(viewModel: ShoppingCartViewModel) {
    // Dummy items can be fetched from the ViewModel or a static data source
    val dummyItems = ShoppingCartDataSource.dummyItems
    val frameTypes = listOf("Wooden Frame", "Metal Frame", "Plastic Frame")
    val photoSizes = listOf("A5", "A4", "A3", "A2")
    val framePrices = mapOf("Wooden Frame" to 200f, "Metal Frame" to 200f, "Plastic Frame" to 666f)
    val sizePrices = mapOf("A5" to 0f, "A4" to 100f, "A3" to 200f, "A2" to 400f)

    Column {
        dummyItems.forEach { item ->
            var selectedFrame by remember { mutableStateOf(frameTypes.first()) }
            var selectedSize by remember { mutableStateOf(photoSizes.first()) }

            Text(text = item.name, modifier = Modifier.padding(8.dp))
            frameTypes.forEach { frame ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedFrame == frame,
                        onClick = { selectedFrame = frame },
                        colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
                    )
                    Text(text = "$frame (+${framePrices[frame]} NOK)")
                }
            }
            photoSizes.forEach { size ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedSize == size,
                        onClick = { selectedSize = size },
                        colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
                    )
                    Text(text = "$size (+${sizePrices[size]} NOK)")
                }
            }
            Button(onClick = {
                val basePrice = item.price // Base price for the photo
                val finalPrice = basePrice + (framePrices[selectedFrame] ?: 0f) + (sizePrices[selectedSize] ?: 0f)
                val newItem = ShoppingCartItem(
                    id = ShoppingCartDataSource.dummyItems.size + 1,
                    name = item.name,
                    frameInfo = "$selectedFrame, $selectedSize",
                    price = finalPrice
                )
                viewModel.addItemToCart(newItem)
            }) {
                Text("Add to Cart")
            }
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
