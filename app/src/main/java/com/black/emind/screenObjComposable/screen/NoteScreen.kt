package com.black.emind.screenObjComposable.screen

import com.black.emind.R
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.black.emind.DEFAULT_CATEGORY
import com.black.emind.NEW_ENTITY
import com.black.emind.getStringResource
import com.black.emind.screenObjComposable.enumScreen.ScreenRouter
import com.black.emind.ui.theme.Gray
import com.black.emind.ui.theme.Orange
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf

@Composable
fun NoteScreen(id: Int){
    BackHandler(onBack = {
      /*  if (bottomDrawerState.isOpen) {
            coroutineScope.launch { bottomDrawerState.close() }
        } else {
            JetNotesRouter.navigateTo(Screen.Notes)
        }*/
        ScreenRouter.reset()
    })
    Scaffold(
        topBar = {
            SaveNoteTopAppBar(id == NEW_ENTITY,
                {ScreenRouter.reset()},
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

//, content: @Composable () -> Unit

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

    val valueTextNote = if (id == NEW_ENTITY)
                            ""
                        else
                            "Edit TextNote"
    var textNote by remember {
        mutableStateOf(valueTextNote)
    }
    val textNoteScrollState = rememberScrollState(0)
    var nameNote by remember {
        mutableStateOf(valueNote)
    }
    var categoryNote by remember {
        mutableStateOf(valueCategory)
    }
    Column(Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
        ) {
            BasicTextField(value = nameNote,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                //  modifier = Modifier.offset(x = (-16).dp),
                textStyle = TextStyle(fontSize = 18.sp),// fontWeight = FontWeight.Bold),
                /*  colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.LightGray
            ),*/
                onValueChange = {
                    nameNote = it
                }
            )
            Row(
                Modifier.padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = getStringResource(R.string.category_note_label),
                    color = Color.Gray,
                    fontSize = 13.sp
                )
                BasicTextField(
                    modifier = Modifier.padding(start = 16.dp),
                    value = categoryNote,
                    readOnly = true,
                    //   modifier = Modifier.offset(x = (-16).dp),
//                                   .verticalScroll(textNoteScrollState),
                    singleLine = true,
                    //textStyle = TextStyle(fontSize = 18.sp),// fontWeight = FontWeight.Bold),
                    /*  colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.LightGray
                ),*/
                    onValueChange = {
                        if (it.length <= MAX_SIZE)
                            categoryNote = it
                    }
                )
            }
            }

            Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .1f))

            LaunchedEffect(textNoteScrollState.maxValue) {
                textNoteScrollState.scrollTo(textNoteScrollState.maxValue)
            }

            TextField(value = textNote,
                modifier = Modifier//.offset(x = (-16).dp)
                    .fillMaxHeight()
                    .verticalScroll(textNoteScrollState),
                //textStyle = TextStyle(fontSize = 18.sp),// fontWeight = FontWeight.Bold),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.LightGray
                ),
                onValueChange = {
                    textNote = it
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