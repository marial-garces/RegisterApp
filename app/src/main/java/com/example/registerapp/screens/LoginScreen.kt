package com.example.registerapp.screens

import android.util.Log.e
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.alpha
import com.example.registerapp.screens.design.AuthOptions
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.registerapp.database.UserDatabase
import com.example.registerapp.database.UserRepository
import com.example.registerapp.database.viewModels.AuthViewModels
import com.example.registerapp.screens.Routes.EDIT_USER
import com.example.registerapp.screens.Routes.LOGIN

@Composable
fun LoginScreen(
    navController: NavController,
) {
    val context = LocalContext.current.applicationContext
    val dao = UserDatabase.getInstance(context.applicationContext).userDao()
    val repo = UserRepository(dao)
    val authVm = viewModel<AuthViewModels>(
        key = "authViewModel",
        factory = AuthViewModels.Factory(repo)
    )

    val emailState = remember { mutableStateOf(TextFieldState()) }
    val passwordState = remember { mutableStateOf(TextFieldState()) }

    val loginResult = authVm.loginResult.observeAsState()

    loginResult.value?.let { result ->
        if (result.isSuccess) {
            val user = result.getOrNull()!!
            navController.navigate("EDIT_USER/${user.userId}"){
                popUpTo(LOGIN){inclusive = true }
            }
        } else {
            Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Spacer(modifier = Modifier.padding(1.dp))

            Image(
                painter = painterResource(id = R.drawable.team_brainstorm),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth(0.25f)
                    .aspectRatio(1f),
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Text(
                text = "Login",
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp
            )
        }

        Spacer(modifier = Modifier.padding(2.dp))

        MyTextField(
            textFieldState = emailState.value,
            hint = "Email",
            leadingIcon = Icons.Outlined.Email,
            trailingIcon = Icons.Outlined.Check,
            keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth()
        )

        MyTextField(
            textFieldState = passwordState.value,
            hint = "Password",
            leadingIcon = Icons.Outlined.Lock,
            trailingText = "Forgot?",
            isPassword = true,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6570cd)
            ),
            onClick = {
                authVm.login(
                    email = emailState.value.text.toString(),
                    password = passwordState.value.text.toString()
                )
            }
        ) {
            Text(
                text = "Login",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(PaddingValues(vertical = 8.dp, horizontal = 16.dp))
            )
        }
        Text(
            text = "Or, login with..",
            fontSize = 16.sp,
            modifier = Modifier
                .align(CenterHorizontally)
                .alpha(0.7f),
            color = Color(0xFF332D41)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            AuthOptions(image = R.drawable.google, tint = Color.Unspecified)
            AuthOptions(image = R.drawable.facebok, tint = Color.Unspecified)
            AuthOptions(image = R.drawable.apple, tint = Color.Unspecified)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have an account? ",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )
            Text(
                text = "Sign Up",
                fontSize = 16.sp,
                color = Color(0xFF332D41),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { navController.navigate(Routes.REGISTER) }
            )
        }

        Spacer(modifier = Modifier.height(1.dp))
    }
}