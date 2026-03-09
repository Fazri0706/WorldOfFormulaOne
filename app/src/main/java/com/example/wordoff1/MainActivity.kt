package com.example.wordoff1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment

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

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "World of F1 – Informasi & Edukasi",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Fazrika Helva Alimni")
        Text("2477051017")

        Spacer(modifier = Modifier.height(20.dp))

        F1Item(
            title = "Max Verstappen",
            description = "Juara dunia bersama Red Bull Racing",
            image = R.drawable.maxverstappen
        )

        Spacer(modifier = Modifier.height(16.dp))

        F1Item(
            title = "Lewis Hamilton",
            description = "Legenda Mantan Tim Mercedes AMG Petronas",
            image = R.drawable.lewishamilton
        )

        Spacer(modifier = Modifier.height(16.dp))

        F1Item(
            title = "Charles Leclerc",
            description = "Pembalap utama Ferrari",
            image = R.drawable.charlesleclerc
        )

        Spacer(modifier = Modifier.height(16.dp))

        F1Item(
            title = "Monaco Grand Prix",
            description = "Balapan ikonik Formula 1",
            image = R.drawable.monacograndprix
        )

        Spacer(modifier = Modifier.height(16.dp))

        F1Item(
            title = "Sejarah Formula 1",
            description = "Dimulai tahun 1950 dan berkembang global",
            image = R.drawable.sejarahformula1_1950
        )
    }
}

@Composable
fun F1Item(title: String, description: String, image: Int) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        Column(
            modifier = Modifier
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = image),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = description,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
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