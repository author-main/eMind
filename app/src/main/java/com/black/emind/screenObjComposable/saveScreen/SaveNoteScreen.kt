package com.black.emind.screenObjComposable.saveScreen

import com.black.emind.R
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.black.emind.DEFAULT_CATEGORY
import com.black.emind.NEW_ENTITY
import com.black.emind.getStringResource
import com.black.emind.screenObjComposable.enumScreen.SaveScreen
import com.black.emind.screenObjComposable.enumScreen.SaveScreenRouter
import com.black.emind.ui.theme.Gray
import com.black.emind.ui.theme.Orange
import androidx.compose.ui.Modifier

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
                //::actionBack,
                {},
                {},
                {})
        },
        content = {
            TitleNote(id)
        }
    )
}

/*fun actionBack(){
    SaveScreenRouter.reset()
}*/

@Composable
private fun TitleNote(id: Int, category: Int = DEFAULT_CATEGORY){
    val MAX_SIZE = 40
    val valueNote = if (id == NEW_ENTITY)
                        getStringResource(R.string.new_note)
                    else
                        "Edit Note"

    val valueCategory = if (category == DEFAULT_CATEGORY)
        getStringResource(R.string.category_note)
    else
        "Edit category"

    var nameNote by remember {
        mutableStateOf(valueNote)
    }
    var categoryNote by remember {
        mutableStateOf(valueCategory)
    }
    Column(modifier = Modifier.padding(start = 16.dp)) {
        TextField(value = nameNote,
            modifier = Modifier.offset(x = -16.dp),
            textStyle = TextStyle(fontSize = 18.sp),// fontWeight = FontWeight.Bold),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.LightGray
            ),
            onValueChange = {
                nameNote = it
            }
        )

            Text(
                text = getStringResource(R.string.category_note_label),
                color = Color.Gray,
                fontSize = 13.sp
            )
            TextField(value = categoryNote,
                modifier = Modifier.offset(x = -16.dp),
                singleLine = true,
                //textStyle = TextStyle(fontSize = 18.sp),// fontWeight = FontWeight.Bold),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.LightGray
                ),
                onValueChange = {
                    if (it.length <=MAX_SIZE)
                        categoryNote = it
                }
            )

    }
}

@Preview
@Composable
private fun TitleNotePreview(){
    TitleNote(id = -1)
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
                fontStyle = FontStyle.Normal,
                text = "Save Note",
                color = Orange//MaterialTheme.colors.onPrimary
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.Close,//ArrowBack,
                    contentDescription = "Save Note Button",
                    tint = Gray//MaterialTheme.colors.onPrimary
                )
            }
        },
        actions = {
            IconButton(onClick = onSaveNoteClick) {
                Icon(
                    imageVector = Icons.Default.Check,
                    tint = Gray,
                    contentDescription = "Save Note"
                )
            }

            IconButton(onClick = onOpenColorPickerClick) {
                Icon(
                    ImageVector.vectorResource(R.drawable.ic_note),
                    contentDescription = "Open Color Picker Button",
                    tint = Gray//MaterialTheme.colors.onPrimary
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