package com.black.emind

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

sealed class SaveScreen {
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