package com.commandhub.mediahub.models

sealed class Screen(val route: String){
    object Home: Screen(route = "home")
    object Content: Screen(route = "content")
}
