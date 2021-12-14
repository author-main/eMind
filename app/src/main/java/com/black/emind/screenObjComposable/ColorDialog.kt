package com.black.emind.screenObjComposable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.black.emind.Dialog
import com.black.emind.DialogRouter
import com.black.emind.R
import com.black.emind.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val COLOR_OBJ = longArrayOf(
    0xffdc4437, 0xffaa47bc, 0xff4286f5, 0xff109d58, 0xff79797d,
    0xfffe5722, 0xffb968c7, 0xff00bcd5, 0xff32ac71, 0xff96969b,
    0xffffa827, 0xfff48fb1, 0xff80deea, 0xff8bc24a, 0xffbebec3,
    0xffffcc80, 0xffe1bee8, 0xffb2dfdc, 0xffcddc39, 0xffe2e2e5
)

/*val COLOR_OBJ_LIGHT = longArrayOf(
    0xffdc4437, 0xffaa47bc, 0xff4286f5, 0xff109d58, 0xff79797d,
    0xfffe5722, 0xffb968c7, 0xff00bcd5, 0xff32ac71, 0xff96969b,
    0xffffa827, 0xfff48fb1, 0xff80deea, 0xff8bc24a, 0xffbebec3,
    0xffffcc80, 0xffe1bee8, 0xffb2dfdc, 0xffcddc39, 0xffe2e2e5
)

val COLOR_OBJ_DARK = longArrayOf(
    0xffdc4437, 0xffaa47bc, 0xff4286f5, 0xff109d58, 0xff79797d,
    0xfffe5722, 0xffb968c7, 0xff00bcd5, 0xff32ac71, 0xff96969b,
    0xffffa827, 0xfff48fb1, 0xff80deea, 0xff8bc24a, 0xffbebec3,
    0xffffcc80, 0xffe1bee8, 0xffb2dfdc, 0xffcddc39, 0xffe2e2e5
)

@Composable
fun getColorObj(index: Int): Color{
    return if (MaterialTheme.colors.isLight)
        Color(COLOR_OBJ_LIGHT[index])
    else
        Color(COLOR_OBJ_DARK[index])
}*/

@Composable
fun CircleColor(index: Int, checked: Boolean = false, onClick: () -> Unit){
    val size = 46.dp
    val color = Color(COLOR_OBJ[index])
    val borderColor = if (checked) {
        val hsv = FloatArray(3)
        android.graphics.Color.colorToHSV(color.toArgb(), hsv)
        hsv[2] = hsv[2] - 0.07f
        Color(android.graphics.Color.HSVToColor(hsv))
    } else
        Color.Transparent
    Box(modifier = Modifier
        .size(size, size)
        //.clip(CircleShape)
        .background(color, CircleShape)
        .border(1.dp, borderColor, CircleShape)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(bounded = true, radius = 20.dp),
            enabled = true
        ) {
            onClick.invoke()
        },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_check_color),
                modifier = Modifier
                    .padding(8.dp)
                    .size(32.dp, 32.dp),
                contentDescription = null
            )
        }
    }

}

/*@Preview
@Composable
fun CircleColorPreview() {
    CircleColor(0)
}*/

@Composable
fun ColorDialog(startIndex: Int = 15/*,
    onPositiveClick: (Color) -> Unit*/
) {
    val route = remember{DialogRouter.currentDialog}

    var indexColor by remember { mutableStateOf(startIndex) }
    //log("indexcolor = $indexColor")
    val scope = rememberCoroutineScope()

    if (route is Dialog.Color) {

        AlertDialog(
            onDismissRequest = {
                DialogRouter.reset()
                //router = Dialog.None
             //   openDialog = false
            },
            title = {
                Text(stringResource(id = R.string.title_change_color))
            },
            text = {

                Column(modifier = Modifier.offset(y = (-12).dp)) {
                    //Text(modifier = Modifier.padding(start = 8.dp, bottom = 8.dp), text = stringResource(id = R.string.title_change_color))
                    COLOR_OBJ.forEachIndexed { index, _ ->
                        if ((index) % 5 == 0) {
                            //log("new row")

                            val currentRow: Int = index / 5
                            //log("index = $index, row = $currentRow")
                            val startIndexRow = currentRow * 5//currentRow * 5
                            val bottomPadding = if (currentRow < 3)
                                8.dp else 0.dp
                            Row(modifier = Modifier.padding(bottom = bottomPadding)) {
                                for (i in 0..4) {
                                    CircleColor(index + i, startIndexRow + i == indexColor) {

                                        scope.launch {
                                            indexColor = index + i
                                            delay(300)
                                            DialogRouter.reset()
                                            //router = Dialog.None

                                            //router = Dialog.None
                                        }
                                    }
                                    if (i < 4)
                                        Spacer(modifier = Modifier.width(8.dp))
                                }
                            }
                        }
                    }
                }
            },
        buttons = {})
    }
}

@Preview
@Composable
fun ColorDialogPreview() {
    ColorDialog(3)
}