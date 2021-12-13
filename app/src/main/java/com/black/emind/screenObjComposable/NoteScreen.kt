package com.black.emind.screenObjComposable

import androidx.compose.runtime.Composable

@Composable
fun NoteTopApp(editMode: Boolean) {
// Если editMode == false - новая заметка
}

@Composable
fun NoteScreen(instance: Any? = null) {
// Если instance is Null - новая заметка
    val editMode = instance != null
    NoteTopApp(editMode)
}