package com.black.emind

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _isShowMenu = MutableLiveData(false)
    val isShowMenu: LiveData<Boolean> = _isShowMenu
}