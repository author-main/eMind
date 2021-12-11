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
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(3.dp)
            .size(56.dp, 56.dp)
            //.background(Orange, CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false, radius = 28.dp),
                enabled = true
            ) {
                viewModel.insertObject(button)
            }
    ) {
        Box(modifier = Modifier
            .size(animatedSize)
            .background(Orange, CircleShape)) {
            Image(
                imageVector = ImageVector.vectorResource(button.icon),
                modifier = Modifier
                    .padding(16.dp)
                    .size(animatedSize),
//                    .background(Orange, CircleShape),
//                alignment = Alignment.Center,
                contentScale = ContentScale.Fit,
                contentDescription = stringResource(id = button.description)
            )
        }
    }
}

@Composable
fun BottomInsertButtons() {
    val viewModel: MainViewModel = viewModel()
    val visibled: Boolean by viewModel.isShowInsertButton.observeAsState(false)
    var visibledPanel by remember{mutableStateOf(false)}
    if (visibled)
        visibledPanel = true
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

    val indexes = if (visibled) arrayOf(0,1,2)
                  else arrayOf(2,1,0)
    val duration = 150
    var animation =  emptyArray<State<Dp>>()
    for (i in 0..2){
        animation += animateDpAsState(
            targetValue = if (visibled) 56.dp else 0.dp,
            animationSpec = tween(
                durationMillis = duration,
                delayMillis = i * 50
            ),
            finishedListener = {
                if (i == indexes[2]) {
                    visibledPanel = false
                }
            }
        )
    }
    Row(modifier = Modifier
        .padding(bottom = 5.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ){
         if (visibledPanel) {
             ButtonInsert(InsertButton.ButtonNote, animation[indexes[0]].value)
             ButtonInsert(InsertButton.ButtonTask, animation[indexes[1]].value)
             ButtonInsert(InsertButton.ButtonDoc,  animation[indexes[2]].value)
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