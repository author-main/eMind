package com.black.emind.screenObjComposable.enumScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.black.emind.NEW_ENTITY

sealed class Screen {
    //var id: Int        = NEW_ENTITY
    object None:       Screen()
    object NoteScreen: Screen()
    object TaskScreen: Screen()
    object DocScreen:  Screen()
}

object ScreenRouter {
    var currentScreen : Screen by mutableStateOf(Screen.None)
    fun navigateTo(destination: Screen){
        currentScreen = destination
    }
    fun reset(){
        currentScreen = Screen.None
    }
}