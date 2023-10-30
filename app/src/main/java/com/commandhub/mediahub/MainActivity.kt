package com.commandhub.mediahub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.commandhub.mediahub.ui.pages.Home
import com.commandhub.mediahub.ui.theme.MediaHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediaHubTheme {
                    Home()
                }
            }
        }
}