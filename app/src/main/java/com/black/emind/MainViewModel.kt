package com.black.emind

import androidx.compose.material.MaterialTheme
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.black.emind.dialogInsertMenu.InsertButton
import com.black.emind.dialogInsertMenu.OnInsertObjectListener
import com.black.emind.screenObjComposable.screen.NoteData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _dataNote = MutableLiveData<NoteData>()
    val dataNote: LiveData<NoteData> = _dataNote

    private val _isShowPanelInsertObj = MutableLiveData(false)
    val isShowPanelInsertObj: LiveData<Boolean> = _isShowPanelInsertObj
    private var onInsertObjectListener: OnInsertObjectListener? = null

    fun setDataNote(value: NoteData = NoteData()){
        _dataNote.value = value
    }

    /*fun setTextNote(value: String){
        _dataNote.value!!.text = value
    }*/

    fun addInsertObjectListener(listener: OnInsertObjectListener){
        onInsertObjectListener = listener
    }
    fun showPanelInsertObj(value: Boolean = true){
        _isShowPanelInsertObj.value = value
    }

    fun insertObject(button: InsertButton){
        fun performClick(){
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
        CoroutineScope(Dispatchers.Main).launch {
            showPanelInsertObj(false)
            delay(200)
            performClick()
        }
    }
}

/*tint = if (MaterialTheme.colors.isLight) textColorLight
else textColorDark*/