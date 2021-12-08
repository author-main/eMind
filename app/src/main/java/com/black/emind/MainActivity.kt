package com.black.emind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.black.emind.bottomnavibar.BottomNavigationBar
import com.black.emind.bottomnavibar.NavigationItem
import com.black.emind.dialogInsertMenu.BottomInsertButtons
import com.black.emind.dialogInsertMenu.OnInsertObjectListener
import com.black.emind.ui.theme.EMindTheme

private lateinit var mainViewModel: MainViewModel

class MainActivity : ComponentActivity(), OnInsertObjectListener{
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel =
            ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.addInsertObjectListener(this)
        setContent {
            EMindTheme {
                MainScreen()
            }
        }
    }

    override fun insertNote() {
        log("Insert Note")
    }

    override fun insertTask() {
        log("Insert Task")
    }

    override fun insertDoc() {
        log("Insert Doc")
    }

}

@ExperimentalMaterialApi
@Composable
fun MainScreen() {
    val viewModel: MainViewModel = viewModel()
    val navController = rememberNavController()
    Scaffold(
     //   topBar = { TopBar() },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) {
        Box(modifier = Modifier.padding(it)//.absoluteOffset(y = (-5).dp)
        ) {
            Navigation(navController)

            /*
            Box(modifier = Modifier
        .padding(3.dp)
        .size(56.dp, 56.dp)
        .background(Orange, CircleShape)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(bounded = false, radius = 28.dp),
            enabled = true
        ) {
            viewModel.insertObject(button)
        }
             */

            Box(modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    viewModel.showInsertButtons(false)
                },
                contentAlignment = Alignment.BottomCenter

            ) {
                BottomInsertButtons()
            }
        }
        /*Box(modifier = Modifier.fillMaxSize()
            .background(Color.Gray),
            contentAlignment = Alignment.BottomCenter
        ) {
            BottomInsertButtons()
        }*/
    }
}

@ExperimentalMaterialApi
@Composable
fun Navigation(navController: NavHostController) {
   NavHost(navController, startDestination = NavigationItem.Emind.route.id) {
        composable(NavigationItem.Search.route.id) {

        }
        composable(NavigationItem.Insert.route.id) {
            //BottomInsertButtons(true)
        }
        composable(NavigationItem.Favorite.route.id) {
        }
        composable(NavigationItem.Settings.route.id) {
          //  BottomSheetDialogMenu()
            //BooksScreen()
        }
        composable(NavigationItem.Emind.route.id) {
            //BooksScreen()
        }
    }
    /*SideEffect {
        currentRoute = navController.currentDestination?.route
        log("currentRoute after = $currentRoute")
    }*/

}