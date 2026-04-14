package com.example.wordoff1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wordoff1.ui.theme.WorldOfF1Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class F1Data(
    val title: String,
    val description: String,
    val image: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WorldOfF1Theme {
                DetailScreen()
            }
        }
    }
}

@Composable
fun DetailScreen() {

    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val dataList = listOf(
        F1Data("Max Verstappen", "Juara dunia Red Bull Racing", R.drawable.maxverstappen),
        F1Data("Lewis Hamilton", "Legenda Mercedes", R.drawable.lewishamilton),
        F1Data("Charles Leclerc", "Pembalap Ferrari", R.drawable.charlesleclerc),
        F1Data("Monaco Grand Prix", "Balapan ikonik", R.drawable.monacograndprix),
        F1Data("Sejarah Formula 1", "Dimulai tahun 1950", R.drawable.sejarahformula1_1950)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.surface
                    )
                )
            )
    ) {

        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {

            item {
                Text(
                    text = "World of F1 – Informasi & Edukasi",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = DividerDefaults.Thickness,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text("Fazrika Helva Alimni")
                Text("2477051017")

                Spacer(modifier = Modifier.height(12.dp))

                Text("Featured", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(12.dp))
            }

            item {
                LazyRow {
                    items(dataList) { item ->
                        FeaturedItem(item)
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
                Text("Semua Data", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(12.dp))
            }

            items(dataList) { item ->
                F1Item(
                    item = item,
                    isLoading = isLoading,
                    onClick = {
                        coroutineScope.launch {
                            isLoading = true
                            delay(2000)

                            snackbarHostState.showSnackbar(
                                "Informasi ${item.title} berhasil dimuat!"
                            )

                            isLoading = false
                        }
                    }
                )
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun FeaturedItem(item: F1Data) {

    Card(
        modifier = Modifier
            .padding(end = 12.dp)
            .width(200.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Column(modifier = Modifier.padding(8.dp)) {

            Image(
                painter = painterResource(item.image),
                contentDescription = item.title,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                item.title,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun F1Item(
    item: F1Data,
    isLoading: Boolean,
    onClick: () -> Unit
) {

    var isFavorite by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box {

                Image(
                    painter = painterResource(item.image),
                    contentDescription = item.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = { isFavorite = !isFavorite },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {

                    Icon(
                        imageVector =
                            if (isFavorite)
                                Icons.Filled.Favorite
                            else
                                Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint =
                            if (isFavorite)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = item.title,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onClick,
                enabled = !isLoading,
                shape = RoundedCornerShape(12.dp)
            ) {

                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Memproses...")
                } else {
                    Text("Pelajari Lebih Lanjut")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    WorldOfF1Theme {
        DetailScreen()
    }
}