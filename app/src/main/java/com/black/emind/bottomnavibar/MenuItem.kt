package com.black.emind.bottomnavibar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class MenuItem(
    @DrawableRes private val icon:    Int,
    @DrawableRes private val icon1:   Int?     = null,
    @StringRes   private val text:    Int,
    @StringRes   private val text1:   Int?     = null,
    )
{
    private var itemVisibled    = true
    private var itemChecked     = false
    private var itemIcon        = icon
    private var itemText        = text
    fun setVisibled(value: Boolean) {
        itemVisibled = value
    }
    fun setChecked(value: Boolean) {
        itemChecked = value
        itemIcon = if (itemChecked && icon1 != null)
                         icon1
                    else icon
        itemText = if (itemChecked && text1 != null)
                        text1
                   else text
    }
    fun isVisibled()    = itemVisibled
    fun isChecked()     = itemChecked
    fun getItemIcon()   = itemIcon
    fun getItemText()   = itemText
}