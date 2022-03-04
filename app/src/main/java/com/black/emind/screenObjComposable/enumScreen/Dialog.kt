package com.black.emind.screenObjComposable.enumScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.black.emind.DEFAULT_FONTCOLOR

sealed class Dialog {
    var onPositiveClick: ((indexColor: Int) -> Unit)? = null
    object None                : Dialog()
    object Color               : Dialog() {
        var startColor: Int = DEFAULT_FONTCOLOR
    }
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