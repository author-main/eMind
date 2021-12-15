package com.black.emind

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

sealed class SaveScreen {
    object None:           SaveScreen()
    object SaveNoteScreen: SaveScreen()
    object SaveTaskScreen: SaveScreen()
    object SaveDocScreen:  SaveScreen()
}

object SaveScreenRouter {
    var currentSaveScreen : SaveScreen by mutableStateOf(SaveScreen.None)
    fun navigateTo(destination: SaveScreen){
        currentSaveScreen = destination
    }
    fun reset(){
        currentSaveScreen = SaveScreen.None
    }
}