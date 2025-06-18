package com.example.registerapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.registerapp.R
import com.example.registerapp.database.User
import com.example.registerapp.database.UserDatabase
import com.example.registerapp.database.UserRepository
import com.example.registerapp.database.viewModels.EditUserViewModel
import com.example.registerapp.screens.Routes.LOGIN
import com.example.registerapp.screens.Routes.WELCOME
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUserScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val user = navController.previousBackStackEntry
        ?.savedStateHandle
        ?.get<User>("user")

    val context = LocalContext.current.applicationContext
    val dao = UserDatabase.getInstance(context).userDao()
    val repo = UserRepository(dao)
    val editVm = viewModel<EditUserViewModel>(
        key = "editViewModel",
        factory = EditUserViewModel.Factory(repo)
    )

    var username by remember { mutableStateOf(user?.userName ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }
    var password by remember { mutableStateOf(user?.password ?: "") }
    var showPassword by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Edit User",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF332D41)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = Color(0xFF332D41)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFDFD6E9)),
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFCCC2DC),
                            Color(0xFFFEF7FF)
                        )
                    )
                ),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome!",
                fontWeight = FontWeight.Bold,
                fontSize = 42.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                color = Color(0xFF332D41)
            )

            Image(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 110.dp)
                    .align(Alignment.CenterHorizontally)
                    .size(200.dp)
            )

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                leadingIcon = {
                    Icon(Icons.Outlined.AccountCircle, null, tint = Color(0xFF84838D))
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                leadingIcon = { Icon(Icons.Outlined.Email, null, tint = Color(0xFF84838D)) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true,
                leadingIcon = {
                    Icon(Icons.Outlined.AccountCircle, null, tint = Color(0xFF84838D))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(
                            imageVector = if (showPassword) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                            contentDescription = null,
                            tint = Color(0xFF84838D)
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
                    .padding(horizontal = 60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6570cd)),
                onClick = {
                    user?.let {
                        val updated = it.copy(userName = username, email = email, password = password)
                        editVm.update(updated.userName, updated.email, updated.password)
                        navController.previousBackStackEntry?.savedStateHandle?.set("user", updated)
                    }
                }
            ) {
                Text(
                    text = "Save Changes",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 120.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF616691)),
                onClick = {
                    navController.navigate(LOGIN) { popUpTo(0) }
                }
            ) {
                Text("Log Out", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            }

            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 70.dp, vertical = 8.dp),
                onClick = {
                    editVm.delete()
                    navController.navigate(LOGIN) { popUpTo(0) }
                }
            ) {
                Text(
                    text = "Delete Account",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF7D5260)
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun EditUserTopBar(navController: NavController, modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(
                text = "Edit User",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF332D41)
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back",
                    tint = Color(0xFF332D41)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFDFD6E9)),
        modifier = Modifier.fillMaxWidth()

    )
}

@Preview
@Composable
fun EditUserScreenPreview() {
    EditUserScreen(
        navController = NavController(LocalContext.current),
    )
}