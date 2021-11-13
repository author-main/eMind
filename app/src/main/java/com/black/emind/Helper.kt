package com.black.emind

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import com.black.emind.AppEMind.Companion.applicationContext
import org.jetbrains.annotations.NotNull


val itemsDescription: Array<String> = getStringArrayResource(R.array.navigationItems)

fun getStringResource(@StringRes id: Int): String =
    applicationContext().resources.getString(id)

fun getStringArrayResource(@ArrayRes id: Int): Array<String> =
    applicationContext().resources.getStringArray(id)