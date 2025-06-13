package com.example.registerapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.registerapp.database.UserDatabase
import com.example.registerapp.database.UserRepository
import com.example.registerapp.database.viewModels.AuthViewModelFactory
import com.example.registerapp.database.viewModels.AuthViewModels
import com.example.registerapp.database.viewModels.EditUserViewModel
import com.example.registerapp.database.viewModels.EditUserViewModelFactory
import com.example.registerapp.screens.EditUserScreen
import com.example.registerapp.screens.LoginScreen
import com.example.registerapp.screens.RegisterScreen
import com.example.registerapp.screens.Routes.EDIT_USER
import com.example.registerapp.screens.Routes.LOGIN
import com.example.registerapp.screens.Routes.REGISTER
import com.example.registerapp.screens.Routes.WELCOME
import com.example.registerapp.screens.WelcomeScreen
import com.example.registerapp.theme.RegisterAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : AppCompatActivity() {
    private val database by lazy {UserDatabase.getInstance(this)}
    private val repository: UserRepository by lazy { UserRepository(database.userDao()) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            RegisterAppTheme {
                val navController = rememberNavController()
                val authFactory = AuthViewModelFactory(repository)
                val editFactory = EditUserViewModelFactory(repository)

                NavHost(
                    navController = navController,
                    startDestination = EDIT_USER,
                    builder = {
                        composable(WELCOME) {
                            WelcomeScreen(navController)
                        }
                        composable(LOGIN) {
                            val authVM: AuthViewModels = viewModel(factory = authFactory)
                            LoginScreen(navController, authVM)
                        }
                        composable(REGISTER) {
                            val authVM: AuthViewModels = viewModel(factory = authFactory)
                            RegisterScreen(navController, authVM)
                        }
                        composable(EDIT_USER) {
                            val editVM: EditUserViewModel = viewModel(factory = editFactory)
                            EditUserScreen(navController, editVM)
                        }
                    }
                )

//                Scaffold(modifier = Modifier.fillMaxWidth()){  innerPadding ->
//
//                    RegisterScreen(modifier = Modifier.padding(innerPadding))
//                }
            }
        }
    }
}