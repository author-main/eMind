package com.black.emind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.black.emind.bottomnavibar.BottomNavigationBar
import com.black.emind.bottomnavibar.NavigationItem
import com.black.emind.dialogInsertMenu.InsertButtonDialog
import com.black.emind.dialogInsertMenu.OnInsertObjectListener
import com.black.emind.screenObjComposable.ColorDialog
import com.black.emind.screenObjComposable.enumScreen.Dialog
import com.black.emind.screenObjComposable.enumScreen.DialogRouter
import com.black.emind.screenObjComposable.enumScreen.SaveScreen
import com.black.emind.screenObjComposable.enumScreen.SaveScreenRouter
import com.black.emind.screenObjComposable.saveScreen.SaveNoteScreen
import com.black.emind.ui.theme.EMindTheme

private lateinit var mainViewModel: MainViewModel

class MainActivity : ComponentActivity(), OnInsertObjectListener{
   // @ExperimentalMaterialApi
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel =
            ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.addInsertObjectListener(this)
        setContent {
            EMindTheme {
                MainScreen()
            }
        }
    }

    override fun insertNote() {
        /*CoroutineScope(Dispatchers.Default).launch {
            delay(200)
            DialogRouter.navigateTo(Dialog.Color)
        }*/
        log("Insert Note")
        SaveScreenRouter.navigateTo(SaveScreen.NoteScreen)
        //DialogRouter.navigateTo(Dialog.Color)
    }

    override fun insertTask() {
        log("Insert Task")
        SaveScreenRouter.navigateTo(SaveScreen.TaskScreen)
    }

    override fun insertDoc() {
        log("Insert Doc")
        SaveScreenRouter.navigateTo(SaveScreen.DocScreen)
    }

}

@ExperimentalMaterialApi

@Composable
fun MainScreen() {
    //val viewModel: MainViewModel = viewModel()
    val navController = rememberNavController()
    Scaffold(
     //   topBar = { TopBar() },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) {
        Box(modifier = Modifier.padding(it)
        ) {
            Navigation(navController)
            when (SaveScreenRouter.currentScreen) {
                is SaveScreen.None          -> {}
                is SaveScreen.NoteScreen    -> {
                    SaveNoteScreen(id = SaveScreen.NoteScreen.id)
                }
                is SaveScreen.TaskScreen    -> {}
                is SaveScreen.DocScreen     -> {}
            }
            when (DialogRouter.currentDialog) {
                is Dialog.None          -> {}
                is Dialog.Color         -> ColorDialog()
                is Dialog.InsertButtons -> InsertButtonDialog()
            }
       }
    }
}

@ExperimentalMaterialApi
@Composable
fun Navigation(navController: NavHostController) {
   NavHost(navController, startDestination = NavigationItem.Emind.data.route) {
        composable(NavigationItem.Search.data.route) {
            //DialogRouter.navigateTo(Dialog.Color)
            //log("navigation search update")
        }
        composable(NavigationItem.Insert.data.route) {
            //DialogRouter.navigateTo(Dialog.InsertButtons)
        }
        composable(NavigationItem.Favorite.data.route) {
        }
        composable(NavigationItem.Settings.data.route) {
          //  BottomSheetDialogMenu()
            //BooksScreen()
        }
        composable(NavigationItem.Emind.data.route) {
            //BooksScreen()
        }
    }
    /*SideEffect {
        currentRoute = navController.currentDestination?.route
        log("currentRoute after = $currentRoute")
    }*/

}