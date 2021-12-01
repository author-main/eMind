package com.black.emind.bottomnavibar

import androidx.annotation.DrawableRes
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.black.emind.*
import com.black.emind.R


const val ID_SEARCH      = "0"
const val ID_EMIND       = "1"
/*const val ID_NOTE        = "1_0"
const val ID_TASK        = "1_1"
const val ID_DOCUMENT    = "1_2"*/

const val ID_INSERT      = "2"
const val ID_FAVORITE    = "3"
const val ID_MENU        = "4"

enum class Route(val id: String) {
    SEARCH(ID_SEARCH),
    EMIND(ID_EMIND),
    //NOTE(ID_NOTE), TASK(ID_TASK), DOCUMENT(ID_DOCUMENT),
    INSERT(ID_INSERT),
    FAVORITE(ID_FAVORITE),
    MENU(ID_MENU)
}

enum class ItemIcon(@DrawableRes val value: Int, val valueOn: Int? = null, val description: String) {
    SEARCH      (R.drawable.ic_search,                  description = itemsDescription[0]),
    /*NOTE        (R.drawable.ic_note,       R.drawable.ic_note_on,     itemsDescription[1]),
    TASK        (R.drawable.ic_task,       R.drawable.ic_task_on,     itemsDescription[2]),
    DOCUMENT    (R.drawable.ic_document,   R.drawable.ic_document_on, itemsDescription[3]),*/
    EMIND       (R.drawable.ic_note,       R.drawable.ic_note_on,     itemsDescription[1]),
    INSERT      (R.drawable.ic_insert,     R.drawable.ic_insert_on,   itemsDescription[2]),
    FAVORITE    (R.drawable.ic_favorite,   R.drawable.ic_favorite_on, itemsDescription[3]),
    MENU        (R.drawable.ic_menu,                    description = itemsDescription[4])
}

sealed class NavigationItem(var route: Route, var icon: ItemIcon){
    object Search:   NavigationItem(Route.SEARCH,     ItemIcon.SEARCH)
    /*
    * object Screen - активный экран:
    * NOTE     - заметки
    * TASK     - список задач
    * DOCUMENT - документы
    */
    /*object Screen:   NavigationItem(Route.NOTE,       ItemIcon.NOTE){
        @JvmName("setRoute1")
        fun setScreen(route: Route) {
            this.route = route
            icon = when (route) {
                Route.TASK      -> ItemIcon.TASK
                Route.DOCUMENT  -> ItemIcon.DOCUMENT
                else            -> ItemIcon.NOTE
            }
        }
    }*/
    object Emind:    NavigationItem(Route.EMIND,     ItemIcon.EMIND)
    object Insert:   NavigationItem(Route.INSERT,     ItemIcon.INSERT)
    object Favorite: NavigationItem(Route.FAVORITE,   ItemIcon.FAVORITE)
    object Menu:     NavigationItem(Route.MENU,       ItemIcon.MENU)
}

@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Search,
        NavigationItem.Emind,
        NavigationItem.Insert,
        NavigationItem.Favorite,
        NavigationItem.Menu
    )
  /*  val showDialog = remember{mutableStateOf(false)}
    if (showDialog.value)
        BottomSheetDialogMenu()*/

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            val selectedItem = currentRoute == item.route.id
            val iconId =
                if (selectedItem)
                    item.icon.valueOn ?: item.icon.value
                else
                    item.icon.value
            BottomNavigationItem(
                icon = {Icon(painterResource(id = iconId), contentDescription = item.icon.description)},
                    selectedContentColor = MaterialTheme.colors.onSecondary,
                    unselectedContentColor = MaterialTheme.colors.secondary,
                    alwaysShowLabel = false,
                    selected = selectedItem,//currentRoute == item.route.id,
                    //selected = false,
                    onClick = {
                        when (item) {
                            is NavigationItem.Menu -> {
                               //showDialog.value = !showDialog.value
                            }
                          /*  is NavigationItem.Screen -> {

                            }*/
                            else -> {
                                navController.navigate(item.route.id) {
                                    //log(item.route.id)
                                    launchSingleTop = true
                                    restoreState = true
                                    navController.graph.startDestinationRoute?.let { route ->
                                        popUpTo(route) {
                                            saveState = true
                                        }
                                    }
                                }
                            }
                        }
                    }
                )

        }
    }
}