package com.example.wordoff1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment

data class F1Data(
    val title: String,
    val description: String,
    val image: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DetailScreen()
        }
    }
}

@Composable
fun DetailScreen() {

    val dataList = listOf(
        F1Data("Max Verstappen","Juara dunia Red Bull Racing",R.drawable.maxverstappen),
        F1Data("Lewis Hamilton","Legenda Mercedes",R.drawable.lewishamilton),
        F1Data("Charles Leclerc","Pembalap Ferrari",R.drawable.charlesleclerc),
        F1Data("Monaco Grand Prix","Balapan ikonik",R.drawable.monacograndprix),
        F1Data("Sejarah Formula 1","Dimulai tahun 1950",R.drawable.sejarahformula1_1950)
    )

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {

        item {
            Text(
                text = "World of F1 – Informasi & Edukasi",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Fazrika Helva Alimni")
            Text("2477051017")

            Spacer(modifier = Modifier.height(16.dp))

            Text("Featured", fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            LazyRow {
                items(dataList) { item ->
                    FeaturedItem(item)
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Semua Data", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(dataList) { item ->
            F1Item(item)
        }
    }
}

@Composable
fun FeaturedItem(item: F1Data) {

    Card(
        modifier = Modifier
            .padding(end = 12.dp)
            .width(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        )
    ) {

        Column(modifier = Modifier.padding(8.dp)) {

            Image(
                painter = painterResource(item.image),
                contentDescription = item.title,
                modifier = Modifier.height(120.dp)
            )

            Text(
                item.title,
                color = Color.White
            )
        }
    }
}

@Composable
fun F1Item(item: F1Data) {

    var isFavorite by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        )
    ) {

        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box {

                Image(
                    painter = painterResource(id = item.image),
                    contentDescription = item.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
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
                                Color.Red
                            else
                                Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = item.title,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = item.description,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            ) {
                Text("Pelajari Lebih Lanjut")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    DetailScreen()
}