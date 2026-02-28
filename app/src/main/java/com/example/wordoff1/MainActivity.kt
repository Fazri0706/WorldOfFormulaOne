package com.example.wordoff1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

data class F1Data(
    val title: String,
    val description: String,
    val imageRes: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            F1App()
        }
    }
}

@Composable
fun F1App() {
    val dataList = listOf(
        F1Data("Max Verstappen",
            "Juara dunia bersama Red Bull Racing",
            R.drawable.maxverstappen),

        F1Data("Lewis Hamilton",
            "Legenda Mantan Tim Mercedes AMG Petronas",
            R.drawable.lewishamilton),

        F1Data("Charles Leclerc",
            "Pembalap utama Ferrari",
            R.drawable.charlesleclerc),

        F1Data("Monaco Grand Prix",
            "Balapan ikonik Formula 1",
            R.drawable.monacograndprix),

        F1Data("Sejarah Formula 1",
            "Dimulai tahun 1950 dan berkembang global",
            R.drawable.sejarahformula1_1950)
    )

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        item {
            Text(
                text = "World of F1 – Informasi & Edukasi",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Fazrika Helva Alimni")
            Text("2477051017")
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(dataList) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = item.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(item.title, style = MaterialTheme.typography.titleMedium)
                    Text(item.description)
                }
            }
        }
    }
}
