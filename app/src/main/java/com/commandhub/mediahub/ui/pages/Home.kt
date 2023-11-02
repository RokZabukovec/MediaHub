package com.commandhub.mediahub.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.commandhub.mediahub.models.Screen
import com.commandhub.mediahub.services.NetworkServiceDiscovery

@Composable
fun Home(navController: NavController) {
    val context = LocalContext.current

    val nsdHelper = remember { NetworkServiceDiscovery(context) }

    DisposableEffect(Unit) {
        nsdHelper.discoverServices()

        onDispose {
            nsdHelper.stopDiscovery()
        }
    }
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)){
        Column(modifier = Modifier.padding(16.dp)) {
            Logo(text = "MediaHub")
            ServerCard("Rok's MBP", "192.168.1.100:8080", navController)
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomePreview() {
    Home(navController = rememberNavController())
}

@Composable
fun Logo(text: String){
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        textAlign = TextAlign.Center,
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 24.sp
    )
}

@Composable
fun ServerCard(origin: String, ipAddress: String, navController: NavController) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSurface,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable {
                navController.navigate(Screen.Content.route)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = origin,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = ipAddress,
                color = Color.Gray,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

