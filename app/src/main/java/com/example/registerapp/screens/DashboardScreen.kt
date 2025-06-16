package com.example.registerapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import androidx.compose.ui.unit.sp
import com.example.registerapp.screens.Routes.DASHBOARD
import com.example.registerapp.screens.Routes.EDIT_USER
import com.example.registerapp.screens.Routes.WELCOME
import com.example.registerapp.screens.design.EmployeeCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(navController = navController)
            }
        },
        gesturesEnabled = false,
    ) {
        Scaffold(
            modifier = modifier,
            topBar = {
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
                    state = rememberTopAppBarState()
                )

                   TopBar(
                       scrollBehavior = scrollBehavior,
                       onOpenDrawer = {
                           scope.launch{
                               drawerState.apply {
                                   if (isClosed) drawerState.open() else drawerState.close()
                               }
                           }
                       },
                   )
            },
            containerColor = Color.Transparent,

        ) { paddingValues ->
            ScreenContent(paddingValues = paddingValues)
        }

    }

}

@Composable
fun DrawerContent(
    navController: NavController
){
    Text(
        text = "Menu",
        fontSize = 28.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
    HorizontalDivider()

    NavigationDrawerItem(
        label = {
            Text("Account",
            fontSize = 17.sp,
            modifier = Modifier.padding(16.dp))
                },
        selected = false,
        onClick = { navController.navigate(EDIT_USER) },
        icon = {
            Icon(Icons.Default.AccountCircle,
                contentDescription = "Account")
        }
    )

    NavigationDrawerItem(
        label = {
            Text("Home",
                fontSize = 17.sp,
                modifier = Modifier.padding(16.dp))
                },
        selected = false,
        onClick = { navController.navigate(DASHBOARD) },
        icon = {
            Icon(Icons.Default.House,
                contentDescription = "Home")
        }
    )

}

@Composable
fun ScreenContent(paddingValues: PaddingValues){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
            color = Color(0xFFCCC2DC)
        ) {
            Column {
                Text(
                    text = "Employees",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF332D41),
                    modifier = Modifier.padding(16.dp)

                )
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(count = 10) { index ->
                        EmployeeCard()
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onOpenDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFE2DBF1)),
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier
                    .padding(start = 18.dp, end = 8.dp)
                    .size(28.dp)
                    .clickable {
                        onOpenDrawer()
                    }
            )
        },
        title = {
                    Text(
                        text = ".",
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
        actions = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Account",
                modifier = Modifier
                    .padding(start = 8.dp, end = 16.dp)
                    .size(30.dp)
            )
        }
    )
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun BottomBar() {
//    val selectedIndex = remember{ mutableStateOf(0) }
//
//    NavigationBar {
//        NavigationBarItem(
//            selected = selectedIndex.value == 0,
//            onClick = { /*TODO*/ },
//            icon = {
//                Icon(
//                    imageVector = Icons.Default.House,
//                    contentDescription = "Home",
//                    modifier = Modifier
//                        .size(30.dp)
//                )
//            }
//        )
//    }

//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color(0xFFE2DBF1))
//            .padding(26.dp),
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Icon(
//            imageVector = Icons.Default.House,
//            contentDescription = "Account",
//            modifier = Modifier
//                .padding(start = 8.dp, end = 16.dp)
//                .size(30.dp)
//                .align(Alignment.CenterVertically)
//        )
//    }
//}








//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(
//                Brush.linearGradient(
//                    colors = listOf(
//                        Color(0xFFCCC2DC),
//                        Color(0xFFFEF7FF),
//                        Color(0xFFFEF7FF),
//                    )
//                )
//            ),
//        verticalArrangement = Arrangement.SpaceEvenly
//    ) {
//        Text(
//            text = "Welcome, Username",
//            style = MaterialTheme.typography.headlineLarge,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            color = MaterialTheme.colorScheme.primary,
//            textAlign = TextAlign.Center
//        )
//
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(2.dp)
//                .background(MaterialTheme.colorScheme.onPrimary)
//        ) {
//            Text(
//                "Employees",
//                color = MaterialTheme.colorScheme.primary,
//                style = MaterialTheme.typography.bodyLarge,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier
//                    .padding(16.dp)
//            )
//        }
//    }