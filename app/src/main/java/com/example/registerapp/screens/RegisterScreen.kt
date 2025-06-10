package com.example.registerapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.registerapp.R
import com.example.registerapp.screens.design.MyTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.alpha
import com.example.registerapp.screens.design.AuthOptions
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
//            horizontalAlignment = CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(16.dp))

            Image(
                painter = painterResource(id = R.drawable.working_w_dog),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth(0.25f)
                    .aspectRatio(1f),
            )

            Spacer(modifier = Modifier.padding(1.dp))

            Text(
                text = "Sign Up",
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp
            )

        }

        Spacer(modifier = Modifier.height(1.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            AuthOptions(image = R.drawable.google, tint = Color.Unspecified)
            AuthOptions(image = R.drawable.facebok, tint = Color.Unspecified)
            AuthOptions(image = R.drawable.apple, tint = Color.Unspecified)
        }

        Spacer(modifier = Modifier.height(1.dp))

        Text(
            text = "Or, sign up with..",
            fontSize = 16.sp,
            modifier = Modifier
                .align(CenterHorizontally)
                .alpha(0.7f)
        )

        Spacer(modifier = Modifier.height(1.dp))

        MyTextField(
            textFieldState = TextFieldState(),
            hint = "Email",
            leadingIcon = Icons.Outlined.Email,
            keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth()
        )

        MyTextField(
            textFieldState = TextFieldState(),
            hint = "Username",
            leadingIcon = Icons.Outlined.AccountCircle,
            modifier = Modifier.fillMaxWidth()
        )

        MyTextField(
            textFieldState = TextFieldState(),
            hint = "Password",
            leadingIcon = Icons.Outlined.Lock,
            isPassword = true,
            modifier = Modifier.fillMaxWidth()
        )


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "Sign Up",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(PaddingValues(vertical = 8.dp, horizontal = 16.dp))
            )
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 1.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Have an account? ",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )
            Text(
                text = "Log in",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable {/* todo */}
            )
        }

        Spacer(modifier = Modifier.height(1.dp))

    }
}