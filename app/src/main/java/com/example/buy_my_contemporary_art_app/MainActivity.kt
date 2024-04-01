package com.example.buy_my_contemporary_art_app

import android.os.Bundle
//import android.annotation.SuppressLint

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.buy_my_contemporary_art_app.data.*
//import com.example.buy_my_contemporary_art_app.data.ShoppingCartDataSource
//import com.example.buy_my_contemporary_art_app.ui.HomeScreen
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

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: ShoppingCartViewModel) {
    //val cartItems by viewModel.cartItems.collectAsState()
    //val scrollState = rememberScrollState()

    Scaffold(
        //modifier = Modifier.verticalScroll(scrollState)
    ) {contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
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
            ShoppingCart(viewModel)
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
            Button(modifier = Modifier.fillMaxWidth(),
                onClick = {
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
fun ShoppingCart(viewModel: ShoppingCartViewModel) {
    val cartItems by viewModel.cartItems.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            "My Shopping Cart",
            style = MaterialTheme.typography.displaySmall
        )
        Divider(color = Color.LightGray, thickness = 1.dp)

        if (cartItems.isEmpty()) {
            Text("Your shopping cart is empty.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)
                .verticalScroll(scrollState)) {
                Column {
                    cartItems.forEach { item ->
                        ShoppingCartItem(item, viewModel)
                    }
                }
            }
            Button(
                onClick = { /* Navigate to payment */ },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
            ) {
                Text("Go to Payment")
            }
        }
    }
}


@Composable
fun ShoppingCartItem(item: ShoppingCartItem, viewModel: ShoppingCartViewModel) {
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
                onClick =  { viewModel.removeItemFromCart(item) },
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
    HomeScreen(viewModel())
}
