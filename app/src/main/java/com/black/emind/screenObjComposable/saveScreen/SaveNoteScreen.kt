package com.black.emind.screenObjComposable.saveScreen

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.black.emind.NEW_ENTITY

@Composable
fun SaveNoteScreen(id: Int){
    Scaffold(
        topBar = {
            SaveNoteTopAppBar(id == NEW_ENTITY,
                {},
                {},
                {},
                {})
        },
        content = {}
    )
}

@Composable
private fun SaveNoteTopAppBar(
    isEditingMode: Boolean,
    onBackClick:            () -> Unit,
    onSaveNoteClick:        () -> Unit,
    onOpenColorPickerClick: () -> Unit,
    onDeleteNoteClick:      () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Save Note",
                color = MaterialTheme.colors.onPrimary
            )
        }
    )
}