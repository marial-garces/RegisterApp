package com.example.registerapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.registerapp.screens.EditUserScreen
import com.example.registerapp.screens.LoginScreen
import com.example.registerapp.screens.RegisterScreen
import com.example.registerapp.screens.Routes
import com.example.registerapp.screens.Routes.EDIT_USER
import com.example.registerapp.screens.Routes.LOGIN
import com.example.registerapp.screens.Routes.REGISTER
import com.example.registerapp.screens.Routes.WELCOME
import com.example.registerapp.screens.WelcomeScreen
import com.example.registerapp.theme.RegisterAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            RegisterAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = EDIT_USER,
                    builder = {
                        composable(WELCOME) {
                            WelcomeScreen(
                                navController = navController,
                                modifier = Modifier.padding(PaddingValues(16.dp))
                            )
                        }
                        composable(LOGIN) {
                            LoginScreen(
                                navController = navController,
                            )
                        }
                        composable(REGISTER) {
                            RegisterScreen(
                                navController = navController,
                            )
                        }
                        composable(EDIT_USER) {
                            EditUserScreen(
                                navController = navController,
                            )
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