package com.black.emind.bottomnavibar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.black.emind.AppEMind.Companion.applicationContext
import com.black.emind.R
import com.black.emind.getStringArrayResource
import com.black.emind.getStringResource
import com.black.emind.itemsDescription

enum class Route {
    SEARCH,
    NOTE, TASK, DOCUMENT,
    INSERT,
    FAVORITE,
    MENU
}

enum class ItemIcon(@DrawableRes val value: Int, val valueOn: Int? = null, val description: String) {
    SEARCH      (R.drawable.ic_search,                  description = itemsDescription[0]),
    NOTE        (R.drawable.ic_note,       R.drawable.ic_note_on,     itemsDescription[1]),
    TASK        (R.drawable.ic_task,       R.drawable.ic_task_on,     itemsDescription[2]),
    DOCUMENT    (R.drawable.ic_document,   R.drawable.ic_document_on, itemsDescription[3]),
    INSERT      (R.drawable.ic_insert,                  description = itemsDescription[4]),
    FAVORITE    (R.drawable.ic_favorite,   R.drawable.ic_favorite_on, itemsDescription[5]),
    MENU        (R.drawable.ic_menu,                    description = itemsDescription[6])
}

sealed class NavigationItem(var route: Route, var icon: ItemIcon){
    object Search:   NavigationItem(Route.SEARCH,     ItemIcon.SEARCH)
    /*
    * object Screen - активный экран:
    * NOTE     - заметки
    * TASK     - список задач
    * DOCUMENT - документы
    */
    object Screen:   NavigationItem(Route.NOTE,       ItemIcon.NOTE){
        @JvmName("setRoute1")
        fun setScreen(route: Route) {
            this.route = route
            icon = when (route) {
                Route.TASK      -> ItemIcon.TASK
                Route.DOCUMENT  -> ItemIcon.DOCUMENT
                else            -> ItemIcon.NOTE
            }
        }
    }
    object Insert:   NavigationItem(Route.INSERT,     ItemIcon.INSERT)
    object Favorite: NavigationItem(Route.FAVORITE,   ItemIcon.FAVORITE)
    object Menu:     NavigationItem(Route.MENU,       ItemIcon.MENU)
}

@Composable
fun BottomNavigationBar() {
    val items = listOf(
        NavigationItem.Search,
        NavigationItem.Screen,
        NavigationItem.Insert,
        NavigationItem.Favorite,
        NavigationItem.Menu
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon.value), contentDescription = item.icon.description) },
              /*  label = { Text(text = item.icon.description) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),*/
                alwaysShowLabel = false,
                selected = false,
                onClick = {
                    /* Add code later */
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}
