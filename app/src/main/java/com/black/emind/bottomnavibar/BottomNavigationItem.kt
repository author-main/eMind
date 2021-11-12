package com.black.emind.bottomnavibar

import androidx.annotation.DrawableRes
import com.black.emind.R

enum class Route {
    SEARCH,
    INSERT,
    FAVORITE,
    MENU
}

sealed class BottomNavigationItem(var route: Route, @DrawableRes var icon: Int){
    object Search:   BottomNavigationItem(Route.SEARCH,     R.drawable.search)
    object Insert:   BottomNavigationItem(Route.INSERT,     R.drawable.insert)
    object Favorite: BottomNavigationItem(Route.FAVORITE,   R.drawable.favorite)
    object Menu:     BottomNavigationItem(Route.MENU,       R.drawable.menu)
}
