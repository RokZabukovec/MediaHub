package com.commandhub.mediahub.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.commandhub.mediahub.models.Screen
import com.commandhub.mediahub.ui.pages.Content
import com.commandhub.mediahub.ui.pages.Home

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route){
        composable(route = Screen.Home.route){
            Home(navController)
        }
        composable(route = Screen.Content.route){
            Content()
        }
    }
}