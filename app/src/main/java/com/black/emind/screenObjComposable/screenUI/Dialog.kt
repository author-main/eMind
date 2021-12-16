package com.black.emind.screenObjComposable.screenUI

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

sealed class Dialog {
    object None         : Dialog()
    object Color        : Dialog()
    object InsertButtons: Dialog()
}

object DialogRouter {
    var currentDialog : Dialog by mutableStateOf(Dialog.None)
    fun navigateTo(destination: Dialog){
        currentDialog = destination
//        log("change route")
    }
    fun reset(){
        currentDialog = Dialog.None
//        log("reset")
    }
}