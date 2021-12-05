package com.black.emind.dialogInsertMenu

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomSheetMenu(context: Context, attr: Int): BottomSheetDialog(context, attr) {
    enum class ItemMenuInsert(val index: Int) {
        ITEM_NOTE(0),
        ITEM_TASK(1),
        ITEM_DOC (2)
    }
    var onClickItemInsertMenu: ((item: ItemMenuInsert) -> Unit)? = null
    /*fun setOnClickItemInsertMenu(value: ((value: ItemMenuInsert) -> Unit)){
        onClickItemInsertMenu = value
    }*/

    /*  private val dataBinding: BottomsheetMenuBinding = DataBindingUtil.inflate(layoutInflater,
        R.layout.bottomsheet_menu, null, false)
    init{
        dataBinding.eventhandler = this
        setContentView(dataBinding.root)
    }
    var onClickMenuItem: ((index: Int) -> Unit)? = null
    fun onClick(v: View){
        onClickMenuItem?.invoke(v.tag.toString().toInt())
        dismiss()
    }*/
}