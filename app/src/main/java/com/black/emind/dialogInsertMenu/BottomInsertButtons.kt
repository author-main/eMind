package com.black.emind.dialogInsertMenu

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.lifecycle.viewmodel.compose.viewModel
import com.black.emind.*
import com.black.emind.R
import com.black.emind.ui.theme.Orange


/*@Composable
fun Clickable(
    onClick: (() -> Unit)? = null,
    consumeDownOnStart: Boolean = false,
    children: @Composable() () -> Unit
) {
    children.invoke()
}*/

sealed class InsertButton(@DrawableRes val icon: Int, @StringRes val description: Int){
    object ButtonNote: InsertButton(R.drawable.ic_insert_note, R.string.button_insert_note)
    object ButtonTask: InsertButton(R.drawable.ic_insert_task, R.string.button_insert_task)
    object ButtonDoc : InsertButton(R.drawable.ic_insert_doc,  R.string.button_insert_doc)
}

@Composable
fun ButtonInsert(button: InsertButton) {
    val viewModel: MainViewModel = viewModel()
    Box(modifier = Modifier
        .padding(3.dp)
        .size(56.dp, 56.dp)
        .background(Orange, CircleShape)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(bounded = false, radius = 28.dp),
            enabled = true
        ) {
            viewModel.insertObject(button)
        }
    ) {
        Image(
            imageVector = ImageVector.vectorResource(button.icon),
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
            contentScale = ContentScale.None,
            contentDescription = stringResource(id = button.description)
        )
    }
}

@Composable
fun BottomInsertButtons() {
    val viewModel: MainViewModel = viewModel()
    val visibled = viewModel.isShowInsertButton.observeAsState()
    Row(modifier = Modifier
        .padding(bottom = 5.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ){
        visibled.value?.let {
            if (it) {
                ButtonInsert(InsertButton.ButtonNote)
                ButtonInsert(InsertButton.ButtonTask)
                ButtonInsert(InsertButton.ButtonDoc)
            }
        }

    }
}

/*
@Composable
fun Toggle(modifier: Modifier = Modifier,
           onToggle: (Boolean) -> Unit) {
    val toggleState: MutableState<Boolean> = mutableStateOf(false)
    TextButton(modifier = modifier, onClick = {
        toggleState.value = !toggleState.value
        onToggle(toggleState.value)
    }) {
        Text(fontSize = 32.sp, text = if (toggleState.value) "Stop" else "Start")
    }
}
 */