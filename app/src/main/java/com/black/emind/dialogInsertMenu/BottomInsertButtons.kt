package com.black.emind.dialogInsertMenu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.black.emind.*
import com.black.emind.R
import com.black.emind.screenObjComposable.enumScreen.DialogRouter
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
    //log("animatedsize $animatedSize")
    val scope = rememberCoroutineScope()
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
                /*scope.launch {
                    viewModel.showPanelInsertObj(false)
                    delay(200)
                    viewModel.insertObject(button)
                }*/
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
                contentScale = ContentScale.Fit,
                contentDescription = stringResource(id = button.description)
            )
        }
    }
}

@Composable
fun BottomInsertButtons() {

    val animate = rememberInfiniteTransition()
    val angle = animate.animateFloat(
        initialValue = 0f,
        targetValue = 56.dp.,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )

   /* var visibled by rememberSaveable {
        mutableStateOf(false)
    }*/
    val viewModel: MainViewModel = viewModel()
    val visibled: Boolean by viewModel.isShowPanelInsertObj.observeAsState(false)
  //  val route = remember{DialogRouter.currentDialog}
    val indexes = if (visibled) arrayOf(0,1,2)
                  else arrayOf(2,1,0)
    val duration = 150
    var animation =  emptyArray<State<Dp>>()
    val target = if (visibled) 56.dp else 0.dp
    for (i in 0..2){
        animation += animateDpAsState(
            targetValue = target,
            animationSpec = tween(
                durationMillis = duration,
                delayMillis = i * 50
            ),
            finishedListener = {
                if (!visibled && i == 0)
                    DialogRouter.reset()
            }
        )
    }
    Row(modifier = Modifier
        .padding(bottom = 5.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ){
             ButtonInsert(InsertButton.ButtonNote, animation[indexes[0]].value)
             ButtonInsert(InsertButton.ButtonTask, animation[indexes[1]].value)
             ButtonInsert(InsertButton.ButtonDoc,  animation[indexes[2]].value)
        /*if (!visibled)
            viewModel.showPanelInsertObj(true)*/
    }
   /* SideEffect {
        if (!visibled)
            viewModel.showPanelInsertObj(true)
    }*/

}

@Composable
fun InsertButtonDialog(){
    val viewModel: MainViewModel = viewModel()
    Surface(modifier = Modifier
        .fillMaxSize()
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {
            viewModel.showPanelInsertObj(false)
        }
        //,color = Color(0x30A0A0A0)
    ) {
       BottomInsertButtons()
       viewModel.showPanelInsertObj(true)
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