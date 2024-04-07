package com.example.buy_my_contemporary_art_app

//import android.annotation.SuppressLint

//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align


//import com.example.buy_my_contemporary_art_app.data.*
//import com.example.buy_my_contemporary_art_app.data.ShoppingCartDataSource
//import com.example.buy_my_contemporary_art_app.ui.HomeScreen
//import com.example.buy_my_contemporary_art_app.ui.ShoppingCartViewModel

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BlurOn
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.buy_my_contemporary_art_app.ui.theme.BuyMyContemporaryArtAppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shoppingCartViewModel by viewModels<ShoppingCartViewModel>()

        setContent {
            BuyMyContemporaryArtAppTheme {
                MyApp(shoppingCartViewModel)
            }
        }
    }
}
@Composable
fun MyApp(viewModel: ShoppingCartViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(viewModel, navController) }
        composable("artists") { ArtistsScreen(viewModel, navController) }
        composable("categories") { CategoriesScreen(viewModel, navController) }
        composable("payment") { PaymentScreen(viewModel, navController) }
        composable("photos/{artistId}") { backStackEntry ->
            PhotosScreen(
                artistId = backStackEntry.arguments?.getString("artistId")?.toLong() ?: -1,
                navController = navController
            )
        }
        composable("photosByCategory/{categoryName}") { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: return@composable
            val category = try {
                Category.valueOf(categoryName)
            } catch (e: IllegalArgumentException) {
                null
            }
            // Ensure that we have a valid category before showing the screen
            category?.let {
                PhotosByCategoryScreen(it, navController)
            }
        }
        composable("photoDetail/{photoId}") { backStackEntry ->
            PhotoDetailScreen(
                photoId = backStackEntry.arguments?.getString("photoId")?.toLong() ?: -1,
                navController = navController,
                viewModel = viewModel() // Ensure you have the ShoppingCartViewModel accessible
            )
        }
    }
}
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: ShoppingCartViewModel, navController: NavController) {
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
            TopAppBar(
                title = { Text("The Art Dealer",
                textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { navController.navigate("artists") }) {
                    Text("Artist")
                }
                Button(onClick = { navController.navigate("categories") }) {
                    Text("Category")
                }
            }
            DummyItemButtons(viewModel)
            ShoppingCart(viewModel, navController)
        }
    }
}

@Composable
fun ArtistPreview() {
    // Provide a dummy ViewModel here if necessary for the preview to work
    ArtistsScreen(viewModel(), rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistsScreen(viewModel: ShoppingCartViewModel, navController: NavController) {
    val context = LocalContext.current
    val dataSourceArtist = DataSourceArtist(context)
    val artists = dataSourceArtist.artists

    Column {

        TopAppBar(
            title = {
                Row {
                    Text("<", modifier = Modifier.clickable {
                        navController.navigate("home")
                    })

                    Text("Choose artist",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
        )

        LazyColumn {
            items(artists) { artist ->
                ListItem(
                    trailingContent = {
                        Image(
                            painter = painterResource(id = artist.imageResId),
                            contentDescription = "${artist.name}'s picture",
                            modifier = Modifier
                                .size(110.dp)
                                .clickable { navController.navigate("photos/${artist.id}") }// Adjust the size as needed
                        )
                    },
                    headlineContent = { Text(artist.name) },
                    modifier = Modifier.clickable {
                        navController.navigate("photos/${artist.id}")
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(viewModel: ShoppingCartViewModel, navController: NavController) {
    val categories = Category.entries.toTypedArray()

    Column {

        TopAppBar(
            title = {
                Row {
                    Text("<", modifier = Modifier.clickable {
                        navController.navigate("home")
                    })

                    Text("Choose category",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
        )

        LazyColumn {
            items(categories) { category ->
                val icon = when (category) {
                    Category.ANIMALS -> Icons.Filled.Pets
                    Category.SPORTS -> Icons.Filled.SportsSoccer
                    Category.FOOD -> Icons.Filled.Fastfood
                    Category.ABSTRACT -> Icons.Filled.BlurOn
                }
                ListItem(
                    trailingContent = {
                        Icon(icon, contentDescription = category.name)
                    },
                    headlineContent = { Text(category.name) },
                    modifier = Modifier.clickable {
                        navController.navigate("photosByCategory/${category.name}")
                    }
                )
            }
        }
    }
}

@Composable
fun DummyItemButtons(viewModel: ShoppingCartViewModel) {
    // Dummy items can be fetched from the ViewModel or a static data source
    val dummyItems = ShoppingCartDataSource.dummyItems
    //val frameTypes = listOf("Wooden Frame"*, "Metal Frame", "Plastic Frame")
    val frameTypes = listOf("Wooden Frame")
    //val photoSizes = listOf("A5", "A4", "A3", "A2")
    val photoSizes = listOf("A5", "A4")
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
fun ShoppingCart(viewModel: ShoppingCartViewModel, navController: NavController) {
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
            LazyColumn {
                items(cartItems) { item ->
                    ShoppingCartItem(item, viewModel)
                }
            }
        }
        Button(
            onClick = { navController.navigate("payment") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Go to Payment")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(viewModel: ShoppingCartViewModel, navController: NavController) {
    val cartItems by viewModel.cartItems.collectAsState()
    val MVA_RATE = 0.25f // The tax rate of 25%
    val totalPriceExclMVA = cartItems.sumOf { it.price.toLong() }
    val MVA = totalPriceExclMVA * MVA_RATE
    val totalPriceInclMVA = totalPriceExclMVA + MVA
    //val context = LocalContext.current

    // State to control popup window visibility
    var showPopup by remember { mutableStateOf(false) }

    // Function to handle the payment process
    fun handlePayment() {
        //viewModel.clearCart() // Clear the shopping cart by a new method (not exist yet)
        // Make a copy of the list of items to be removed to avoid concurrent modification.
        val itemsToRemove = cartItems.toList()
        itemsToRemove.forEach { item -> viewModel.removeItemFromCart(item) }
        showPopup = true // Show the popup
    }
    if (showPopup) {
        // Popup Window
        AlertDialog(
            onDismissRequest = { showPopup = false /* Dismiss dialog */ },
            title = { Text(text = "Payment") },
            text = { Text("Your payment is being performed") },
            confirmButton = {
                Button(
                    onClick = {
                        showPopup = false // Dismiss the popup
                        navController.navigate("home") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                ) {Text("OK") }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
    ) {
        TopAppBar(
            title = {
                Row {
                    Text("<", modifier = Modifier.clickable {
                        navController.navigate("artists")
                    })

                    Text(
                        "Photos to buy",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Top frame with summary
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    cartItems.forEach { item ->
                        Text("Name: ${item.name}")
                        Text("Frame info: ${item.frameInfo}")
                        Text("Price: ${item.price}")
                        Divider()
                    }
                    Text("Price excl. MVA: $totalPriceExclMVA")
                    Text("MVA: $MVA")
                    Text("Total price: $totalPriceInclMVA")
                }
            }

            // Lower frame with payment fields
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    var cardNumber by remember { mutableStateOf("") }
                    var expirationDate by remember { mutableStateOf("") }
                    var cvvNumber by remember { mutableStateOf("") }

                    OutlinedTextField(
                        value = cardNumber,
                        onValueChange = { cardNumber = it },
                        label = { Text("Card Number") }
                    )
                    OutlinedTextField(
                        value = expirationDate,
                        onValueChange = { expirationDate = it },
                        label = { Text("Expiration Date") }
                    )
                    OutlinedTextField(
                        value = cvvNumber,
                        onValueChange = { cvvNumber = it },
                        label = { Text("CVV Number") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { handlePayment() },
                        modifier = Modifier.fillMaxWidth(),
                        content = { Text("Pay") }
                    )
                }
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
        Column(modifier = Modifier){
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
            }
            Button(
                onClick =  { viewModel.removeItemFromCart(item) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {Text("D E L E T E") }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotosListScreen(
    artistId: Long? = null,
    category: Category? = null,
    navController: NavController
) {
    val photos = when {
        artistId != null -> photosByArtist(artistId)
        category != null -> photosByCategory(category)
        else -> listOf()
    }

    Column {
        TopAppBar(title = { Text("Photos") })
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(photos) { photo ->
                PhotoCard(photo, navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoDetailScreen(photoId: Long, navController: NavController, viewModel: ShoppingCartViewModel) {
    val cartItems by viewModel.cartItems.collectAsState()

    val scrollState = rememberScrollState()
    val photo = DataSourcePhotos.allPhotos.find { it.id == photoId }
    if (photo == null) {
        Text("Photo not found")
        return
    }

    val frameTypes = listOf("Wooden Frame", "Metal Frame", "Plastic Frame")
    val photoSizes = listOf("A5", "A4", "A3", "A2")
    val framePrices = mapOf("Wooden Frame" to 200f, "Metal Frame" to 300f, "Plastic Frame" to 100f)
    val sizePrices = mapOf("A5" to 50f, "A4" to 100f, "A3" to 150f, "A2" to 200f)

    var selectedFrame by remember { mutableStateOf(frameTypes.first()) }
    var selectedSize by remember { mutableStateOf(photoSizes.first()) }

    Column(modifier = Modifier
        //.padding(8.dp)
        .verticalScroll(scrollState)
    ){
        TopAppBar(
            title = {
                Row {
                    Text("<", modifier = Modifier.clickable {
                        navController.navigate("photos/${photo.artist.id}")
                    })

                    Text(
                        photo.title,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
        )

        Image(
            painter = painterResource(id = photo.imageResId),
            contentDescription = photo.title,
            modifier = Modifier
                .height(470.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .padding(8.dp)
        )
        Text(
            text = "Price: " + photo.price.toString() + " NOK",
            //style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(8.dp)
        )
        Divider(modifier = Modifier.padding(vertical = 0.dp))

        Text("Select Frame", modifier = Modifier.padding(8.dp))
        frameTypes.forEach { frame ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedFrame == frame,
                    onClick = { selectedFrame = frame }
                )
                Text(text = "$frame (+${framePrices[frame]} NOK)")
            }
        }

        Text("Select Size", modifier = Modifier.padding(horizontal = 8.dp))
        photoSizes.forEach { size ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedSize == size,
                    onClick = { selectedSize = size }
                )
                Text(text = "$size (+${sizePrices[size]} NOK)")
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            onClick = {
                val basePrice = photo.price // Assuming each photo object has a base price
                val finalPrice = basePrice + (framePrices[selectedFrame] ?: 0f) + (sizePrices[selectedSize] ?: 0f)
                val newItem = ShoppingCartItem(
                    id = (Math.random() * 10000).toInt(), // Example ID generation
                    name = "${photo.title}: $selectedSize $selectedFrame",
                    frameInfo = "$selectedFrame, $selectedSize",
                    price = finalPrice
                )
                viewModel.addItemToCart(newItem)
                navController.navigate("home") // Assuming "cart" is the route to the shopping cart screen
            }
        ) {
            Text("Add to Cart")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotosScreen(artistId: Long, navController: NavController) {
    // Assume DataSourcePhotos is an object that contains a list of all photos
    val photos = photosByArtist(artistId)

    Column {

        TopAppBar(
            title = {
                Row {
                    Text("<", modifier = Modifier.clickable {
                        navController.navigate("artists")
                    })

                    Text(
                        "Photos",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
        )

        // Setting up a grid layout for the photos
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Adjust the number of grid cells as needed
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            items(photos) { photo ->
                PhotoCard(photo, navController)
            }
        }
    }
}

@Composable
fun PhotoCard(photo: Photo, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { navController.navigate("photoDetail/${photo.id}") },
        //elevation = 4.dp
    ) {
        Column {
            Image(
                painter = painterResource(id = photo.imageResId),
                contentDescription = photo.title,
                modifier = Modifier
                    .height(150.dp) // Adjust the height as needed
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
            )
            Text(
                text = photo.title,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

fun photosByArtist(artistId: Long): List<Photo> {
    // This will filter the photos by the artist's ID
    return DataSourcePhotos.allPhotos.filter { it.artist.id == artistId }
}
fun photosByCategory(category: Category): List<Photo> {
    // This will filter the photos by the given category
    return DataSourcePhotos.allPhotos.filter { it.category == category }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotosByCategoryScreen(category: Category, navController: NavController) {
    // This will filter the photos by the category
    val photos = photosByCategory(category)

    Column {

        TopAppBar(
            title = {
                Row {
                    Text("<", modifier = Modifier.clickable {
                        navController.navigate("categories")
                    })

                    Text(
                        "Photos",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Adjust the number of grid cells as needed
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            items(photos) { photo ->
                PhotoCard(photo, navController)
            }
        }
    }
}

@Preview(name = "home screen")
@Composable
fun DefaultPreview() {
    // Provide an empty ViewModel here if necessary for the preview to work
    //HomeScreen(viewModel(), rememberNavController())
    // This creates a new instance of the ViewModel, not the same one used at runtime
    val viewModel = ShoppingCartViewModel().apply {
        addItemToCart(ShoppingCartItem(1, "Landscape Painting", "Wooden Frame", 150f))
    }

//@Preview
@Composable
fun PhotosByArtistScreenPreview() {
        PhotosScreen(Artist(0, "Danny Lee", R.drawable.food0).id, rememberNavController())
}

    BuyMyContemporaryArtAppTheme {
        HomeScreen(viewModel, rememberNavController())
    }
}

@Preview
@Composable
fun PhotosByArtistScreenPreview() {
    PhotosScreen(Artist(0, "Danny Lee", R.drawable.food0).id, rememberNavController())
}

@Preview
@Composable
fun PhotosByCategoryScreenPreview() {
    PhotosByCategoryScreen(Category.FOOD, rememberNavController())
}

@Preview(name = "payment screen")
@Composable
fun PaymentPreview() {
    // Provide a dummy ViewModel here if necessary for the preview to work
    PaymentScreen(viewModel(), rememberNavController())
}

//////////////////////////////////////////////////////////////////
// ViewModel classes
data class ShoppingCartItem(
    val id: Int,
    val name: String,
    val frameInfo: String,
    val price: Float
)
class ShoppingCart {
    private val _items = mutableListOf<ShoppingCartItem>()
    val items: List<ShoppingCartItem> get() = _items.toList()

    fun addItem(item: ShoppingCartItem) {
        _items.add(item)
    }

    fun removeItem(item: ShoppingCartItem) {
        _items.remove(item)
    }
}
class ShoppingCartViewModel : ViewModel() {
    private val _cart = ShoppingCart()
    private val _cartItems = MutableStateFlow<List<ShoppingCartItem>>(_cart.items)
    val cartItems: StateFlow<List<ShoppingCartItem>> = _cartItems.asStateFlow()

    fun addItemToCart(item: ShoppingCartItem) {
        _cart.addItem(item).also { Log.d("ShoppingCart", "Item added: $item") }
        _cartItems.value = _cart.items //also { Log.d("ShoppingCart", "Cart items updated: $_cart.items") }
        //_cartItems.update { _cart.items }
    }

    fun removeItemFromCart(item: ShoppingCartItem) {
        _cart.removeItem(item)
        _cartItems.value = _cart.items
        //_cartItems.update { _cart.items }
    }

/*    fun clearCart() {
        _cart.items.clear() // Clear the shopping cart items
        _cartItems.value = _cart.items // Update the cart items state
    }*/
}

//////////////////////////////////////////////////////////////////
// basic classes
data class Photo(
    val id: Long,
    val title: String = "",
    @DrawableRes
    val imageResId: Int,
    val artist: Artist,
    val category: Category,
    var price: Float = 0.0f
)
enum class Category { ANIMALS, SPORTS, FOOD, ABSTRACT }

data class Artist(
    val id: Long,
    val name: String,
    @DrawableRes val imageResId: Int
)
class DataSourceArtist(context: Context){
    val artists = listOf(
        Artist(0, context.getString(R.string.billy), R.drawable.billyherr),
        Artist(1, context.getString(R.string.anthony), R.drawable.vit),
        Artist(2, context.getString(R.string.tj), R.drawable.cummings),
        Artist(3, context.getString(R.string.danny), R.drawable.danny),
    )
}

//////////////////////////////////////////////////////////////////
// DATASOURCE classes
object ShoppingCartDataSource {
    val dummyItems = listOf(
        ShoppingCartItem(1, "Landscape Painting", "Wooden Frame", 150f),
        //ShoppingCartItem(2, "Abstract Artwork", "Metal Frame", 200f),
        //ShoppingCartItem(3, "Portrait", "Plastic Frame", 100f),
        //ShoppingCartItem(4, "Modern Art", "Metal Frame", 300f)
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
