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
/*
 * object Screen - активный экран:
 * NOTE     - заметки
 * TASK     - список задач
 * DOCUMENT - документы
 */
sealed class BottomNavigationItem(var route: Route, @DrawableRes var icon: Int){

    object Search:   BottomNavigationItem(Route.SEARCH,     R.drawable.search)
    /*
    * object Screen - активный экран:
    * NOTE     - заметки
    * TASK     - список задач
    * DOCUMENT - документы
    */
    object Screen:   BottomNavigationItem(Route.NOTE, R.drawable.note){
        @JvmName("setRoute1")
        fun setRoute(route: Route) {
            this.route = route
            icon = when (route) {
                Route.TASK -> R.drawable.task
                Route.DOCUMENT -> R.drawable.document
                else -> R.drawable.note
            }
        }
    }
    object Insert:   BottomNavigationItem(Route.INSERT,     R.drawable.insert)
    object Favorite: BottomNavigationItem(Route.FAVORITE,   R.drawable.favorite)
    object Menu:     BottomNavigationItem(Route.MENU,       R.drawable.menu)
}
