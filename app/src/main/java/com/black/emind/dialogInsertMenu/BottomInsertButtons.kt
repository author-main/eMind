package com.black.emind.dialogInsertMenu

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
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
import com.black.emind.AppEMind
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import com.black.emind.R
import com.black.emind.getColorTheme
import com.black.emind.log
import com.black.emind.ui.theme.Orange


/*@Composable
fun Clickable(
    onClick: (() -> Unit)? = null,
    consumeDownOnStart: Boolean = false,
    children: @Composable() () -> Unit
) {
    children.invoke()
}*/

@Composable
fun ButtonInsert(@DrawableRes icon: Int, description: String?) {
    Box(modifier = Modifier
        .padding(3.dp)
        .size(56.dp, 56.dp)
        .background(Orange, CircleShape)
        .clickable (
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(bounded = false, radius = 32.dp),
            enabled = true,
            role = Role.Image){
            log("Click")
        }
    ) {
    Image(
            ImageVector.vectorResource(icon),
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
            contentScale = ContentScale.None,
            contentDescription = description
        )
    }
}

@Composable
fun BottomInsertButtons() {
    Row(modifier = Modifier
        .padding(bottom = 5.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ){
        ButtonInsert(icon = R.drawable.ic_insert_note, description = stringResource(id = R.string.button_insert_note))
        ButtonInsert(icon = R.drawable.ic_insert_task, description = stringResource(id = R.string.button_insert_task))
        ButtonInsert(icon = R.drawable.ic_insert_doc, description = stringResource(id = R.string.button_insert_doc))
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