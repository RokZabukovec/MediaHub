package com.commandhub.mediahub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.commandhub.mediahub.ui.SetupNavGraph
import com.commandhub.mediahub.ui.theme.MediaHubTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MediaHubTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
                }
            }
        }
}