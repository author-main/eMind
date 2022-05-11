package com.black.emind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.black.emind.screenObjComposable.enumScreen.Screen
import com.black.emind.screenObjComposable.enumScreen.ScreenRouter
import com.black.emind.screenObjComposable.screen.NoteData
import com.black.emind.screenObjComposable.screen.NoteScreen
import com.black.emind.ui.theme.EMindTheme

class MainActivity : ComponentActivity(), OnInsertObjectListener{

    /*private val model: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }*/

    private lateinit var model: MainViewModel

   // @ExperimentalMaterialApi
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* val model: MainViewModel by viewModels()
        model.addInsertObjectListener(this)*/
        model =
            ViewModelProvider(this)[MainViewModel::class.java]
        model.addInsertObjectListener(this)
        setContent {
            EMindTheme {
                MainScreen()
            }
        }
    }

    override fun insertNote() {
        model.changeDataNote() // новая заметка NoteData()
        ScreenRouter.navigateTo(Screen.NoteScreen)
    }

    override fun insertTask() {
        //log("Insert Task")
        ScreenRouter.navigateTo(Screen.TaskScreen)
    }

    override fun insertDoc() {
        //log("Insert Doc")
        ScreenRouter.navigateTo(Screen.DocScreen)
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
            when (ScreenRouter.currentScreen) {
                is Screen.None          -> {}
                is Screen.NoteScreen    -> {
                    //(viewModel() as MainViewModel).changeDataNote(NoteData())
                    NoteScreen()//id = Screen.NoteScreen.id)
                }
                is Screen.TaskScreen    -> {}
                is Screen.DocScreen     -> {}
            }
            when (DialogRouter.currentDialog) {
                is Dialog.None          -> {}
                is Dialog.Color         -> {
                    Dialog.Color.onPositiveClick?.let {onClick ->
                        ColorDialog(startIndexColor = Dialog.Color.startColor, onPositiveClick = onClick)
                    }
                }
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