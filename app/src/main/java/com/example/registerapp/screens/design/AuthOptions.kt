package com.example.registerapp.screens.design


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size


@Composable
fun AuthOptions(
    modifier: Modifier = Modifier,
    image: Int,
    tint: Color? = null,
    contentDescription: String? = null,
) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
                shape = RoundedCornerShape(14.dp)
            )
            .clip(RoundedCornerShape(14.dp))
            .clickable{}
            .padding(horizontal = 35.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ){
        if(tint != null){
            Icon(
                painter = painterResource(image),
                contentDescription = contentDescription,
                tint = tint,
                modifier = Modifier.size(30.dp)
            )
        }else {
            Icon(
                painter = painterResource(image),
                contentDescription = contentDescription,
                modifier = Modifier.size(30.dp),
            )
        }
    }
}