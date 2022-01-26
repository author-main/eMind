package com.black.emind.screenObjComposable.enumScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.black.emind.NEW_ENTITY

sealed class SaveScreen {
    var id: Int        = NEW_ENTITY
    object None:       SaveScreen()
    object NoteScreen: SaveScreen()
    object TaskScreen: SaveScreen()
    object DocScreen:  SaveScreen()
}

object SaveScreenRouter {
    var currentScreen : SaveScreen by mutableStateOf(SaveScreen.None)
    fun navigateTo(destination: SaveScreen){
        currentScreen = destination
    }
    fun reset(){
        currentScreen = SaveScreen.None
    }
}