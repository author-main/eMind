package com.black.emind.screenObjComposable.saveScreen

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.black.emind.NEW_ENTITY
import com.black.emind.ui.theme.Orange

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
                color = Orange//MaterialTheme.colors.onPrimary
            )
        }
    )
}