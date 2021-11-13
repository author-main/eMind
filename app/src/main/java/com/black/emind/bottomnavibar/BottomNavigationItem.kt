package com.black.emind.bottomnavibar

import androidx.annotation.DrawableRes
import com.black.emind.R

enum class Route {
    SEARCH,
    NOTE, TASK, DOCUMENT,
    INSERT,
    FAVORITE,
    MENU
}

enum class ItemIcon(@DrawableRes val icon: Int, @DrawableRes val iconSelect: Int) {
    SEARCH      (R.drawable.search,     R.drawable.search_on),
    NOTE        (R.drawable.note,       R.drawable.note_on),
    TASK        (R.drawable.task,       R.drawable.task_on),
    DOCUMENT    (R.drawable.document,   R.drawable.document_on),
    INSERT      (R.drawable.insert,     R.drawable.insert_on),
    FAVORITE    (R.drawable.favorite,   R.drawable.favorite_on),
    MENU        (R.drawable.menu,       R.drawable.menu_on)
}

sealed class BottomNavigationItem(var route: Route, var icon: ItemIcon){
    object Search:   BottomNavigationItem(Route.SEARCH,     ItemIcon.SEARCH)
    /*
    * object Screen - активный экран:
    * NOTE     - заметки
    * TASK     - список задач
    * DOCUMENT - документы
    */
    object Screen:   BottomNavigationItem(Route.NOTE, ItemIcon.NOTE){
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
    object Insert:   BottomNavigationItem(Route.INSERT,     ItemIcon.INSERT)
    object Favorite: BottomNavigationItem(Route.FAVORITE,   ItemIcon.FAVORITE)
    object Menu:     BottomNavigationItem(Route.MENU,       ItemIcon.MENU)
}
