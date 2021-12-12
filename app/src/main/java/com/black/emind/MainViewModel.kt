package com.black.emind

import androidx.compose.material.MaterialTheme
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.black.emind.dialogInsertMenu.InsertButton
import com.black.emind.dialogInsertMenu.OnInsertObjectListener

class MainViewModel: ViewModel() {
    private val _isShowInsertButtons = MutableLiveData(false)
    val isShowInsertButton: LiveData<Boolean> = _isShowInsertButtons

    private val _isShowPanelInsertObj = MutableLiveData(false)
    val isShowPanelInsertObj: LiveData<Boolean> = _isShowPanelInsertObj

    private var onInsertObjectListener: OnInsertObjectListener? = null
    fun addInsertObjectListener(listener: OnInsertObjectListener){
        onInsertObjectListener = listener
    }

    fun showInsertButtons(value: Boolean = true){
        _isShowInsertButtons.value = value
        if (value)
            _isShowPanelInsertObj.value = true
    }
    fun showPanelInsertObj(value: Boolean = true){
        _isShowPanelInsertObj.value = value
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
        showInsertButtons(false)
    }
}

/*tint = if (MaterialTheme.colors.isLight) textColorLight
else textColorDark*/