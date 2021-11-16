package com.black.emind.bottomnavibar

import androidx.annotation.DrawableRes
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.black.emind.*
import com.black.emind.R


const val ID_SEARCH      = "0"
const val ID_NOTE        = "1_0"
const val ID_TASK        = "1_1"
const val ID_DOCUMENT    = "1_2"
const val ID_INSERT      = "2"
const val ID_FAVORITE    = "3"
const val ID_MENU        = "4"

enum class Route(val id: String) {
    SEARCH(ID_SEARCH),
    NOTE(ID_NOTE), TASK(ID_TASK), DOCUMENT(ID_DOCUMENT),
    INSERT(ID_INSERT),
    FAVORITE(ID_FAVORITE),
    MENU(ID_MENU)
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
fun BottomNavigationBar(navController: NavController) {
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

        /*val currentDestination = navController.currentDestination?.route
        if (currentDestination == NavigationItem.Menu.route.id)
            return@BottomNavigation*/

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        log(currentRoute)
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon.value), contentDescription = item.icon.description)},
              /*  label = { Text(text = item.icon.description) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),*/
                alwaysShowLabel = false,
                selected = currentRoute == item.route.id,
                //selected = false,
                onClick = {
                    when (item) {
                        is NavigationItem.Menu -> {

                        }
                        is NavigationItem.Screen -> {

                        }
                        else -> {
                            navController.navigate(item.route.id) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                }
            )
        }
    }
}