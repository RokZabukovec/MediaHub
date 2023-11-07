package com.commandhub.mediahub.ui.pages

import android.net.nsd.NsdServiceInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.commandhub.mediahub.models.NsdViewModel
import com.commandhub.mediahub.models.Screen


@Composable
fun Home(navController: NavController) {
    val viewModel: NsdViewModel = viewModel()
    val services = viewModel.services

    DisposableEffect(Unit) {
        viewModel.startDiscovery()

        onDispose {
            viewModel.stopDiscovery()
        }
    }

    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background).fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Logo(text = "MediaHub")

            if (services.isEmpty()) {
                LoadingBar("Looking for Media Hub server...")
            } else {
                LazyColumn {
                    items(services) { service ->
                        ServerCard(service.serviceName, "${service.host.hostAddress}:${service.port}", navController)
                    }
                }
            }
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
        color = MaterialTheme.colorScheme.onBackground,
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

@Composable
fun LoadingBar(message: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = MaterialTheme.colorScheme.primary
        )
    }
}


