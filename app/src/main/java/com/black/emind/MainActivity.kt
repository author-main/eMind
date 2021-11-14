package com.black.emind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.black.emind.bottomnavibar.BottomNavigationBar
import com.black.emind.bottomnavibar.NavigationItem
import com.black.emind.ui.theme.EMindTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EMindTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
     //   topBar = { TopBar() },
        bottomBar = { BottomNavigationBar() }
    ) {
        /* Add code later */
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Screen.route.id) {
        composable(NavigationItem.Search.route.id) {
            //HomeScreen()
        }
        composable(NavigationItem.Insert.route.id) {
            //MusicScreen()
        }
        composable(NavigationItem.Favorite.route.id) {
            //MoviesScreen()
        }
        composable(NavigationItem.Menu.route.id) {
            //BooksScreen()
        }
        composable(NavigationItem.Screen.route.id) {
            log(NavigationItem.Screen.route.id)
            //BooksScreen()
        }
    }
}