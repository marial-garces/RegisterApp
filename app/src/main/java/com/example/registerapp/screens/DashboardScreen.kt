package com.example.registerapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.registerapp.database.User
import com.example.registerapp.database.api.EmployeesUiState
import com.example.registerapp.database.api.EmployeesViewModel
import com.example.registerapp.database.viewModels.UserManager
import com.example.registerapp.screens.Routes.DASHBOARD
import com.example.registerapp.screens.Routes.EDIT_USER
import com.example.registerapp.screens.design.EmployeeCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    employeeViewModel: EmployeesViewModel  = viewModel()
) {
    val currentUser = UserManager.currentUser
    val uiState by employeeViewModel.uiState.collectAsState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor = Color(0xFFE2DBF1)) {
                DrawerContent(navController = navController, user = currentUser)
            }
        },
        gesturesEnabled = true,
    ) {
        Scaffold(
            modifier = modifier,
            topBar = {
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
                    state = rememberTopAppBarState()
                )
                TopBar(
                    scrollBehavior = scrollBehavior,
                    userName = currentUser?.userName ?: "Loading...",
                    onOpenDrawer = {
                        scope.launch {
                            if (drawerState.isClosed) drawerState.open() else drawerState.close()
                        }
                    },
                    navController = navController,
                    user = currentUser,
//                    onRefesh = { employeeViewModel.retryLoading() }
                )
            },
            containerColor = Color(0xFFCCC2DC),
        ) { paddingValues ->
            ScreenContent(
                paddingValues = paddingValues,
                uiState = uiState,
                onRetry = { employeeViewModel.retryLoading() }
            )
        }
    }
}

@Composable
fun DrawerContent(navController: NavController, user: User?) {
    Text(
        text = "Menu",
        fontSize = 28.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        fontWeight = FontWeight.SemiBold,
    )
    HorizontalDivider(color = Color(0xFF8A888F))

    NavigationDrawerItem(
        label = {
            Text("Account", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
        },
        selected = false,
        onClick = {
            navController.navigate(EDIT_USER)
        },
        icon = {
            Icon(Icons.Default.AccountCircle, contentDescription = "Account")
        }
    )

    NavigationDrawerItem(
        label = {
            Text("Home", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
        },
        selected = false,
        onClick = {
            navController.navigate(DASHBOARD)
        },
        icon = {
            Icon(Icons.Default.House, contentDescription = "Home")
        }
    )
}

@Composable
fun ScreenContent(
    paddingValues: PaddingValues,
    uiState: EmployeesUiState,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE2DBF1)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Employees",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF1A1A1A)
                        )

                        if (uiState.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp,
                                color = Color(0xFF6750A4)
                            )
                        }
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun TopBar(
    onOpenDrawer: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    userName: String,
    user: User?,
    navController: NavController
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFCCC2DC)),
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier
                    .padding(start = 18.dp, end = 8.dp)
                    .size(28.dp)
                    .clickable { onOpenDrawer() }
            )
        },
        title = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome,",
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "${userName}!",
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    navController.navigate(EDIT_USER)
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Account"
                )
            }
        }
    )
}

@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(
        navController = NavController(LocalContext.current),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopBarPreview() {
    val mockUser = User(
        userName = "marialaura", email = "m.garces21@gmail.com",
        userId = 7,
        password = "12345678"
    )
    TopBar(
        onOpenDrawer = {},
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
        userName = mockUser.userName,
        navController = NavController(LocalContext.current),
        user = mockUser
    )
}
