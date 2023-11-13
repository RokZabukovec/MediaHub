package com.commandhub.mediahub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.commandhub.mediahub.ui.theme.OnPastelMint
import com.commandhub.mediahub.ui.theme.PastelMint

@Composable
fun PageDetailCard() {
    Box(
        modifier = Modifier
            .height(100.dp)
            .clip(RoundedCornerShape(16.dp)) // Rounded corners
            .background(PastelMint)
            .padding(15.dp)){
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth())
        {
            Column{
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = "Roks-MBP",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = OnPastelMint

                    )
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = OnPastelMint,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Row {
                    Text(text = "3 movies", modifier = Modifier.padding(0.dp, 0.dp, 15.dp, 0.dp))
                    Text(text = "150 images")
                }
            }
        }
    }
}