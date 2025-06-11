package com.example.registerapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.registerapp.screens.Routes.WELCOME

@Composable
fun EditUserScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
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
            ),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Welcome!",
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 1.dp)
                .padding(bottom = 1.dp)
                .align(Alignment.CenterHorizontally),
            color = Color(0xFF332D41)
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(36.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFEF7FF),
                contentColor = Color(0xFF6570cd)
            ),
            border = BorderStroke(2.dp, Color(0xFF6570cd)),
            onClick = { navController.navigate(WELCOME) }
        ) {
            Text(
                text = "Log Out",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(PaddingValues(vertical = 8.dp, horizontal = 16.dp))
            )
        }
    }


}