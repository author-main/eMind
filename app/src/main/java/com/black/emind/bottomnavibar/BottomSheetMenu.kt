package com.black.emind.bottomnavibar

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import com.black.emind.R
import com.black.emind.databinding.BottomsheetMenuBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


class BottomSheetMenu(context: Context, attr: Int): BottomSheetDialog(context, attr) {
    val ITEM0 = 0
    val ITEM1 = 1
    val ITEM2 = 2
    val ITEM3 = 3
    val ITEM4 = 4
    private val dataBinding: BottomsheetMenuBinding = DataBindingUtil.inflate(layoutInflater,
        R.layout.bottomsheet_menu, null, false)
    init{
        dataBinding.eventhandler = this
        setContentView(dataBinding.root)
    }
    var onClickMenuItem: ((index: Int) -> Unit)? = null
    fun onClick(v: View){
        onClickMenuItem?.invoke(v.tag.toString().toInt())
    }
}