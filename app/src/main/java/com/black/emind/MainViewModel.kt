package com.black.emind

import androidx.compose.material.MaterialTheme
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.black.emind.dialogInsertMenu.InsertButton
import com.black.emind.dialogInsertMenu.OnInsertObjectListener

class MainViewModel: ViewModel() {
    private val _isShowPanelInsertObj = MutableLiveData(false)
    val isShowPanelInsertObj: LiveData<Boolean> = _isShowPanelInsertObj

    private var onInsertObjectListener: OnInsertObjectListener? = null
    fun addInsertObjectListener(listener: OnInsertObjectListener){
        onInsertObjectListener = listener
    }

    fun showPanelInsertObj(value: Boolean = true){
        _isShowPanelInsertObj.value = value
    }

    fun insertObject(button: InsertButton){
        //DialogRouter.reset()
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
        showPanelInsertObj(false)
    }
}

/*tint = if (MaterialTheme.colors.isLight) textColorLight
else textColorDark*/