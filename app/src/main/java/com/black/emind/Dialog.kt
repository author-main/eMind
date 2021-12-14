package com.black.emind

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Dialog {
    object None         : Dialog()
    object Color        : Dialog()
    object InsertButtons: Dialog()
}

object Router {
    var currentDialog: MutableState<Dialog> = mutableStateOf(Dialog.None)
    fun navigateTo(destination: Dialog){
        currentDialog.value = destination
    }
}