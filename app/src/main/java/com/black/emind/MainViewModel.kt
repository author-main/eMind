package com.black.emind

import androidx.compose.material.MaterialTheme
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.black.emind.dialogInsertMenu.InsertButton
import com.black.emind.dialogInsertMenu.OnInsertObjectListener

class MainViewModel: ViewModel() {
    /*private val _isShowMenu = MutableLiveData(false)
    val isShowMenu: LiveData<Boolean> = _isShowMenu*/
    private var onInsertObjectListener: OnInsertObjectListener? = null
    fun addInsertObjectListener(listener: OnInsertObjectListener){
        onInsertObjectListener = listener
    }
    fun insertObject(button: InsertButton){
        when (button) {
            is InsertButton.ButtonNote ->{
                onInsertObjectListener?.insertNote()
            }
            is InsertButton.ButtonTask ->{
                onInsertObjectListener?.insertTask()
            }
            is InsertButton.ButtonDoc ->{
                onInsertObjectListener?.insertDoc()
            }
        }
    }
}

/*tint = if (MaterialTheme.colors.isLight) textColorLight
else textColorDark*/