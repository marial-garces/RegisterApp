
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.registerapp.database.UserDatabase
import com.example.registerapp.database.UserDatabaseDao
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
                val dao = UserDatabase.getInstance(applicationContext).userDao()
                val allUsers =   dao.getAllUsers().observeAsState(emptyList())

                NavHost(
                    navController = navController,
                    startDestination = WELCOME,
                    builder = {
                        composable(WELCOME) {
                            WelcomeScreen(
                                navController = navController,
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
                        composable(
                            route = "$EDIT_USER/{userId}",
                            arguments = listOf(navArgument("userId") { type = NavType.LongType })
                        ) {backStackEntry ->
                            val userId = backStackEntry.arguments?.getLong("userId") ?: 0L
                            EditUserScreen(navController = navController,userId = userId)
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
