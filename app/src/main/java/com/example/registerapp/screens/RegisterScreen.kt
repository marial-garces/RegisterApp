package com.example.registerapp.screens

import android.widget.Toast
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
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.draw.alpha
import com.example.registerapp.screens.design.AuthOptions
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.registerapp.database.UserDatabase
import com.example.registerapp.database.UserRepository
import com.example.registerapp.database.viewModels.AuthViewModelFactory
import com.example.registerapp.database.viewModels.AuthViewModels
import com.example.registerapp.screens.Routes.LOGIN


@Composable
fun RegisterScreen(
    navController: NavController,
    authVM: AuthViewModels
) {
    val context = LocalContext.current.applicationContext
    val dao = UserDatabase.getInstance(context).userDao()
    val repo = UserRepository(dao)

    val authVm: AuthViewModels = viewModel(
        key = "AuthViewModel",
        factory = AuthViewModelFactory(repo)
    )

    val usernameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    val registerResult = authVm.registerResult.observeAsState()

    LaunchedEffect(registerResult) {
        registerResult.value?.let { result ->
            if (result.isSuccess) {
                val userId = result.getOrNull()
                Toast.makeText(
                    context,
                    "Registration successful! User ID: ${result.getOrNull()}",
                    Toast.LENGTH_SHORT
                ).show()
                navController.navigate(LOGIN)
            } else {
                Toast.makeText(
                    context,
                    "Registration failed: ${result.exceptionOrNull()?.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column() {
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
                fontSize = 30.sp,
                color = Color(0xFF332D41)
            )

        }

        Spacer(modifier = Modifier.height(5.dp))

        MyTextField(
            textFieldState = TextFieldState,
            hint = "Username",
            leadingIcon = Icons.Outlined.AccountCircle,
            modifier = Modifier.fillMaxWidth(),
        )

        MyTextField(
            textFieldState = TextFieldState,
            hint = "Email",
            leadingIcon = Icons.Outlined.Email,
            keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth(),

            )

        MyTextField(
            textFieldState = TextFieldState,
            hint = "Password",
            leadingIcon = Icons.Outlined.Lock,
            isPassword = true,
            modifier = Modifier.fillMaxWidth()
        )


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6570cd)
            ),
            onClick = {
                navController.navigate(LOGIN)
            }
        ) {
            Text(
                text = "Sign Up",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(PaddingValues(vertical = 8.dp, horizontal = 16.dp))
            )
        }

        Spacer(modifier = Modifier.height(1.dp))

        Text(
            text = "Or, sign up with..",
            fontSize = 16.sp,
            modifier = Modifier
                .align(CenterHorizontally)
                .alpha(0.7f),
            color = Color(0xFF332D41)
        )

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
                color = Color(0xFF332D41),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { navController.navigate(LOGIN) }
            )
        }

        Spacer(modifier = Modifier.height(1.dp))

    }
}