package com.black.emind.dialogInsertMenu

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
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
fun ButtonInsert(button: InsertButton, animatedSize: Dp){//modifier: Modifier = Modifier) {
    val viewModel: MainViewModel = viewModel()
    Box(modifier = Modifier
        .padding(3.dp)
        .size(animatedSize)
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
    val visibled: Boolean by viewModel.isShowInsertButton.observeAsState(false)
    /*val value by animateFloatAsState(
        targetValue = 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )*/
    /*val rotationAngle by animateFloatAsState(
        targetValue = if (visibled) 360F else 0f,
        animationSpec = tween(
            durationMillis = 1500
        )
    )
    val rotationAngle1 by animateFloatAsState(
        targetValue = if (visibled) 360F else 0f,
        animationSpec = tween(
            durationMillis = 1500,
            delayMillis = 100
        )
    )
    val rotationAngle2 by animateFloatAsState(
        targetValue = if (visibled) 360F else 0f,
        animationSpec = tween(
            durationMillis = 1500,
            delayMillis = 200
        )
    )*/
    val duration = 100
    val animatedSize: Dp by animateDpAsState(
        targetValue = if (visibled) 56.dp else 0.dp,
        animationSpec = tween(
            durationMillis = duration
        )
    )

    val animatedSize1: Dp by animateDpAsState(
        targetValue = if (visibled) 56.dp else 0.dp,
        animationSpec = tween(
            durationMillis = duration,
            delayMillis = 50
        )
    )

    val animatedSize2: Dp by animateDpAsState(
        targetValue = if (visibled) 56.dp else 0.dp,
        animationSpec = tween(
            durationMillis = duration,
            delayMillis = 100
        )
    )

    Row(modifier = Modifier
        .padding(bottom = 5.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ){
         //if (visibled) {
             ButtonInsert(InsertButton.ButtonNote, animatedSize)
             ButtonInsert(InsertButton.ButtonTask, animatedSize1)
             ButtonInsert(InsertButton.ButtonDoc,  animatedSize2)
        // }

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