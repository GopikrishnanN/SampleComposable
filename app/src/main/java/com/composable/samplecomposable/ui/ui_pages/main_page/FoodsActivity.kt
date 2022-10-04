package com.composable.samplecomposable.ui.ui_pages.main_page

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.composable.samplecomposable.R
import com.composable.samplecomposable.ui.theme.SampleComposableTheme
import com.composable.samplecomposable.ui.ui_pages.main_page.billing_page.BillingScreen
import com.composable.samplecomposable.ui.ui_pages.main_page.cart_page.CartScreen
import com.composable.samplecomposable.ui.ui_pages.main_page.home_page.HomeScreen
import com.composable.samplecomposable.ui.ui_pages.main_page.home_page.SurfaceView
import com.composable.samplecomposable.ui.ui_pages.main_page.profile_page.ProfileScreen
import com.composable.samplecomposable.ui.ui_pages.main_page.search_page.SearchScreen
import com.composable.samplecomposable.ui.ui_pages.main_page.search_page.SearchSurfaceView
import kotlin.math.roundToInt

class FoodsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleComposableTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldView()
                }
            }
        }
    }
}

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldView() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = Color.White,
        bottomBar = { FoodsBottomBar(navController) },
    ) {
        NavBody(navController)
    }
}

@Composable
fun NavBody(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { SurfaceView() }
        composable("categories") { SearchSurfaceView() }
        composable("cart") { CartScreen() }
        composable("billing") { BillingScreen("Billing Screen") }
        composable("account") { ProfileScreen("Account Screen") }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppScreen(text: String) = Surface(modifier = Modifier.fillMaxSize()) {
    Scaffold(backgroundColor = Color.White, content = {
        Text(
            text = text,
            style = TextStyle(color = Color.Black)
        )
    })
}

@Composable
fun FoodsBottomBar(navController: NavHostController) {
    val bottomBarOffsetHeightPx = remember { mutableStateOf(0f) }
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.white),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
    ) {
        BottomAppBar(
            backgroundColor = colorResource(id = R.color.white),
            modifier = Modifier
//                .height(50.dp)
                .offset {
                    IntOffset(
                        x = 0,
                        y = -bottomBarOffsetHeightPx.value.roundToInt()
                    )
                }) {
            BottomScreenNavController(navController)
        }
    }
}

@Composable
fun BottomScreenNavController(navController: NavHostController) {

    val bottomNavigationItems = listOf(
        NavigationBarItems.Home,
        NavigationBarItems.Categories,
        NavigationBarItems.Cart,
        NavigationBarItems.Billing,
        NavigationBarItems.Account,
    )

    BottomNavigation(
        backgroundColor = Color.White, modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route
        BottomNavigation(
            backgroundColor = colorResource(id = R.color.black),
            contentColor = Color.White,
        ) {
            bottomNavigationItems.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = item.icon),
                            modifier = Modifier
                                .height(30.dp)
                                .width(30.dp)
                                .padding(vertical = 5.dp),
                            contentDescription = item.title
                        )
                    },
//                    label = { Text(text = item.title, style = TextStyle(fontSize = 11.sp)) },
                    selectedContentColor = Color.Red,
                    unselectedContentColor = Color.White,
                    alwaysShowLabel = true,
                    selected = currentDestination == item.route,
                    onClick = {
                        navController.navigate(item.route)
                    }
                )
            }
        }
    }
}
