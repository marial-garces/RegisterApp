package com.example.registerapp.screens

import android.widget.EditText
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.registerapp.R
import com.example.registerapp.database.UserDatabase
import com.example.registerapp.database.UserRepository
import com.example.registerapp.database.viewModels.AuthViewModels
import com.example.registerapp.database.viewModels.EditUserViewModel
import com.example.registerapp.screens.Routes.LOGIN
import com.example.registerapp.screens.Routes.WELCOME

@Composable
fun EditUserScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    userId: Long
) {

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    val context = LocalContext.current.applicationContext
    val dao = UserDatabase.getInstance(context.applicationContext).userDao()
    val repo = UserRepository(dao)
    val editVm = viewModel<EditUserViewModel>(
        key = "editViewModel",
        factory = EditUserViewModel.Factory(repo)
    )

    LaunchedEffect(userId) {
        editVm.loadById(userId)
    }

    val userState = editVm.currentUser.observeAsState()
    userState.value?.let { user ->
        LaunchedEffect(user) {
            username = user.userName
            email = user.email
            password = user.password
        }
    }

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
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Welcome!",
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            color = Color(0xFF332D41)
        )

        Column {
            Image(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 110.dp)
                    .align(Alignment.CenterHorizontally)
                    .size(200.dp),
                alignment = Alignment.Center,
            )
        }

        //username
        OutlinedTextField(
            value = username,
            onValueChange = { username = it},
            label = { Text("Username") },
            leadingIcon = { Icon(Icons.Outlined.AccountCircle, null, tint = Color(0xFF84838D)) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
        )


        //email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it},
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = { Icon(Icons.Outlined.Email, null, tint = Color(0xFF84838D))},
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
        )

        //password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it},
            label = { Text("Password") },
            singleLine = true,
            leadingIcon = { Icon(Icons.Outlined.AccountCircle, null, tint = Color(0xFF84838D)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if(showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if(showPassword) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                        contentDescription = null, tint = Color(0xFF84838D)
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
                .padding(vertical = 1.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6570cd)
            ),
            onClick = {
                editVm.update(username,email,password)
            }
        ) {
            Text(
                text = "Save Changes",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(PaddingValues(
                        vertical = 4.dp, horizontal = 8.dp
                    ))
            )
        }

        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 70.dp)
                .padding(bottom = 20.dp),
            onClick = { navController.navigate(WELCOME) }
        ) {
            Text(
                text = "Log Out",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF7D5260),
                modifier = Modifier
                    .padding(
                        PaddingValues(
                            vertical = 4.dp, horizontal = 8.dp
                        )
                    )
                    .clickable {
                        editVm.logout()
                        navController.navigate(LOGIN){
                            popUpTo(0)
                        }
                    }
            )
        }
    }
}