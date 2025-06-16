package com.example.registerapp.screens.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.registerapp.R
import com.example.registerapp.theme.RegisterAppTheme

@Composable
fun EmployeeCard(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(7.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0EBFC)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        CardContent(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFCCC2DC),
                            Color(0xFFFEF7FF),
                            Color(0xFFFEF7FF),
                        )
                    )
                )
        )
    }
}


@Composable
private fun CardContent(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .padding(12.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Dashboard Icon",
            modifier = Modifier
                .size(80.dp)
                .aspectRatio(1f)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(18.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            //Full name
            Text(
                text = ("María Garcés"),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            //Email
            Text(
                text = ("m.garces@gmail.com"),
                fontSize = 13.sp,
            )
        }


    }
}





@Preview(showBackground = true, widthDp = 320)
@Composable
fun DashboardScreenPreview() {
    RegisterAppTheme {
        EmployeeCard()
    }
}

@Preview(showBackground = true)
@Composable
fun CardDashboardPreview() {
    RegisterAppTheme {
        CardContent()
    }
}