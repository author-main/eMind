package com.black.emind.screenObjComposable.enumScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.black.emind.DEFAULT_FONTCOLOR
import com.black.emind.screenObjComposable.COLOR_OBJ

sealed class Dialog {
    var onPositiveClick: ((color: androidx.compose.ui.graphics.Color) -> Unit)? = null
    object None         : Dialog()
    object Color        : Dialog() {
        var startColor  = DEFAULT_FONTCOLOR
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