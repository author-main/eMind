package com.black.emind.screenObjComposable.saveScreen

import androidx.activity.compose.BackHandler
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.black.emind.NEW_ENTITY
import com.black.emind.screenObjComposable.enumScreen.SaveScreen
import com.black.emind.screenObjComposable.enumScreen.SaveScreenRouter
import com.black.emind.ui.theme.Orange

@Composable
fun SaveNoteScreen(id: Int){
    BackHandler(onBack = {
      /*  if (bottomDrawerState.isOpen) {
            coroutineScope.launch { bottomDrawerState.close() }
        } else {
            JetNotesRouter.navigateTo(Screen.Notes)
        }*/
        SaveScreenRouter.reset()
    })
    Scaffold(
        topBar = {
            SaveNoteTopAppBar(id == NEW_ENTITY,
                {SaveScreenRouter.reset()},
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
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Save Note Button",
                    tint = Orange//MaterialTheme.colors.onPrimary
                )
            }
        }

    )
}

/*
{
    "editor.fontFamily": "JetBrains Mono Light",
    "workbench.colorTheme": "GitHub Dark Dimmed",
    "workbench.iconTheme": "material-icon-theme",
    "editor.fontSize": 15,
    "editor.fontLigatures":true
}
*/