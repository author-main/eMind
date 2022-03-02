package com.black.emind.screenObjComposable.screen

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.black.emind.screenObjComposable.enumScreen.ScreenRouter
import com.black.emind.ui.theme.Gray
import com.black.emind.ui.theme.Orange
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.lifecycle.viewmodel.compose.viewModel
import com.black.emind.*
import com.black.emind.R
import com.black.emind.screenObjComposable.enumScreen.Dialog
import com.black.emind.screenObjComposable.enumScreen.DialogRouter

@Composable
fun NoteScreen(){//(id: Int){
    val viewModel: MainViewModel = viewModel()
    //val noteEntry: NoteData by (viewModel() as MainViewModel).dataNote.observeAsState(NoteData()) //Entry - запись
    val noteEntry: NoteData by viewModel.dataNote.collectAsState()

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
            SaveNoteTopAppBar(true,//id == NEW_ENTITY,
                {ScreenRouter.reset()},
                //::actionBack,
                onChangeFontSize = {
                    val fontSize = if (noteEntry.fontSize > MAX_FONTSIZE)
                                        DEFAULT_FONTSIZE
                                    else
                                        noteEntry.fontSize + 3
                    viewModel.changeDataNote(noteEntry.copy(fontSize = fontSize))
                },
                onChangeFontColor = {
                 //   log("${noteEntry.fontColor}")
                    Dialog.Color.startColor = noteEntry.fontColor
                    Dialog.Color.onPositiveClick = {color ->
                        viewModel.changeDataNote(noteEntry.copy(fontColor = color))
                    }
                    DialogRouter.navigateTo(Dialog.Color)

                    /*val fontSize = if (noteEntry.fontSize > MAX_FONTSIZE)
                        DEFAULT_FONTSIZE
                    else
                        noteEntry.fontSize + 3
                    viewModel.changeDataNote(noteEntry.copy(fontSize = fontSize))*/
                },
                {
                   //viewModel.setTextNote(";jgf")
                },
                {},
                {})
        },
        content = {
            //TitleNote(id)
            TitleNote(noteEntry)
        }
    )
}




//, content: @Composable () -> Unit

@Composable
private fun TitleNote(noteEntry: NoteData){//id: Int, category: Int = DEFAULT_CATEGORY){
    //val MAX_SIZE = 40
    val viewModel: MainViewModel = viewModel()
   // log ("${noteEntry.fontSize}")

  /*  val valueCategory = if (noteEntry.category == DEFAULT_CATEGORY)
        getStringResource(R.string.category_note)
    else
        "Edit category"*/


    val textNoteScrollState = rememberScrollState(0)

  /*  val categoryNote by remember {
        mutableStateOf(valueCategory)
    }*/

    Column(Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
        ) {
            BasicTextField(value = noteEntry.name,
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
                    viewModel.changeDataNote(noteEntry.copy(name = it))
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
                    value = viewModel.getCategoryNoteName(noteEntry.category),
                    modifier = Modifier.padding(start = 16.dp),
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
                      /*  if (it.length <= MAX_SIZE)
                            noteEntry.category = it*/
                    }
                )
            }
            }

            Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .1f))

            LaunchedEffect(textNoteScrollState.maxValue) {
                textNoteScrollState.scrollTo(textNoteScrollState.maxValue)
            }

            TextField(value = noteEntry.text,//textNote,
                modifier = Modifier//.offset(x = (-16).dp)
                    .fillMaxHeight()
                    .verticalScroll(textNoteScrollState),
                textStyle = TextStyle(fontSize = noteEntry.fontSize.sp,
                                        color = noteEntry.fontColor),// fontWeight = FontWeight.Bold),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.LightGray
                ),
                onValueChange = {
                    /*textNote = it
                    noteEntry.text = it*/
                    viewModel.changeDataNote(noteEntry.copy(text = it))
                }
            )


    }
}

/*@Preview(showBackground = true)
@Composable
private fun TitleNotePreview(){
    TitleNote()
}*/

@Composable
fun IconExt(@DrawableRes iconResource: Int, action: () -> Unit, description: String? = null){
    Icon( modifier = Modifier.clickable(
        onClick = action,
        enabled = true,
        indication = rememberRipple(color = Orange, bounded = false),
        interactionSource =  remember { MutableInteractionSource() },
    ),
        imageVector = ImageVector.vectorResource(iconResource),
        contentDescription = description,
        tint = Gray//MaterialTheme.colors.onPrimary
    )
}

@Composable
private fun SaveNoteTopAppBar(
    isEditingMode: Boolean,
    onBackClick:            () -> Unit,
    onChangeFontSize:       () -> Unit,
    onChangeFontColor:      () -> Unit,
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
            IconButton(onClick = {}/*,
                modifier = Modifier.clickable(
                    onClick = onBackClick,
                    enabled = true,
                    indication = rememberRipple(color = Orange, bounded = false),
                    interactionSource =  remember { MutableInteractionSource() },
                )*/
            )
             {
                 IconExt(iconResource = R.drawable.ic_favorite, onBackClick)

                 /*
                Icon( modifier = Modifier.clickable(
                    onClick = onBackClick,
                    enabled = true,
                    indication = rememberRipple(color = Orange, bounded = false),
                    interactionSource =  remember { MutableInteractionSource() },
                ),
                    imageVector = Icons.Default.Close,//ArrowBack,
                    contentDescription = "Save Note Button",
                    tint = Gray//MaterialTheme.colors.onPrimary
                )*/
            }
        },
        actions = {
            IconButton(onClick = { }
            ) {
                IconExt(iconResource = R.drawable.ic_fontsize, onChangeFontSize)
               /* Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_fontsize),//Icons.Default.Check,
                    tint = Gray,
                    contentDescription = "Save Note"
                )*/
            }

            IconButton(onClick = { } ) {
                IconExt(iconResource = R.drawable.ic_fontcolor, onChangeFontColor)
              /*  Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_fontcolor),
                    contentDescription = "Open Color Picker Button",
                    tint = Gray//MaterialTheme.colors.onPrimary
                )*/
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