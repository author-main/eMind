package com.black.emind.ui.theme

import android.R.id
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import android.R.id.primary
import android.R.id.primary







private val DarkColorPalette = darkColors(
    primary = DarkGray,
    primaryVariant = Purple700,
    secondary = White,
    onSecondary = Orange


)

private val LightColorPalette = lightColors(
    primary = White,
    //primaryVariant = Purple700,
    secondary = Gray,
    onSecondary = Orange


/*
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
*/
)

@Composable
fun EMindTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    /*Чтобы поделиться состоянием компоновки с объектами,
    не управляемыми компоновкой, используйте компонент
    Sideffect composable, так как он вызывается при каждой
    успешной перекомпозиции.*/


    val systemUiController = rememberSystemUiController()
    if(darkTheme){
        systemUiController.setSystemBarsColor(
            color = Color.Transparent
        )
    }else{

       /* systemUiController.setSystemBarsColor(
            color = LightColorPalette.primary
        )*/
        /*systemUiController.setStatusBarColor(
            color = LightColorPalette.primary
        )
        systemUiController.setNavigationBarColor(
            color = LightColorPalette.primary
        )*/
        systemUiController.setSystemBarsColor(
            color = LightColorPalette.primary,
           /* transformColorForLightContent = {
                Color.White
            },*/
          //  darkIcons = false
            //isNavigationBarContrastEnforced = true
        )


    }


    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}