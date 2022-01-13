package com.black.emind.bottomnavibar

import androidx.annotation.DrawableRes
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.black.emind.*
import com.black.emind.R
import com.black.emind.screenObjComposable.enumScreen.Dialog
import com.black.emind.screenObjComposable.enumScreen.DialogRouter
import com.black.emind.ui.theme.Gray
import com.black.emind.ui.theme.Orange


const val ID_SEARCH      = "0"
const val ID_EMIND       = "1"
const val ID_INSERT      = "2"
const val ID_FAVORITE    = "3"
const val ID_SETTINGS    = "4"

enum class Route(val id: String) {
    SEARCH(ID_SEARCH),
    EMIND(ID_EMIND),
    INSERT(ID_INSERT),
    FAVORITE(ID_FAVORITE),
    SETTINGS(ID_SETTINGS)
}

enum class ItemIcon(@DrawableRes var value: Int, val valueOn: Int? = null, val description: String) {
    SEARCH      (R.drawable.ic_search,     R.drawable.ic_search_on,     itemsDescription[0]),
    EMIND       (R.drawable.ic_files,      R.drawable.ic_files_on,      itemsDescription[1]),
    INSERT      (R.drawable.ic_insert,     R.drawable.ic_insert_on,     itemsDescription[2]),
    FAVORITE    (R.drawable.ic_favorite,   R.drawable.ic_favorite_on,   itemsDescription[3]),
    SETTINGS    (R.drawable.ic_settings,   R.drawable.ic_settings_on,   itemsDescription[4])
}

sealed class NavigationItem(var route: Route, var icon: ItemIcon){
    object Search:   NavigationItem(Route.SEARCH,     ItemIcon.SEARCH)
    object Emind:    NavigationItem(Route.EMIND,      ItemIcon.EMIND)
    object Insert:   NavigationItem(Route.INSERT,     ItemIcon.INSERT)
    object Favorite: NavigationItem(Route.FAVORITE,   ItemIcon.FAVORITE)
    object Settings: NavigationItem(Route.SETTINGS,   ItemIcon.SETTINGS)
}

@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(navController: NavController) {
    //val context = LocalContext.current
    val viewModel: MainViewModel = viewModel()
   // val visibled: Boolean by viewModel.isShowInsertButton.observeAsState(false)
    //log("visibled $visibled")
    /*var counter by remember { mutableStateOf(1) }
    log("counter = $counter")*/
    val items = listOf(
        NavigationItem.Settings,
        NavigationItem.Search,
        NavigationItem.Insert,
        NavigationItem.Favorite,
        NavigationItem.Emind
    )
    if (DialogRouter.currentDialog is Dialog.InsertButtons)
        NavigationItem.Insert.icon.value = R.drawable.ic_insert_on
    else
        NavigationItem.Insert.icon.value = R.drawable.ic_insert
  /*  val showDialog = remember{mutableStateOf(false)}
    if (showDialog.value)
        BottomSheetDialogMenu()*/

    BottomNavigation(
      /*  Modifier.onGloballyPositioned {
            log("${it.size.height}")
        },*/
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
           /* val unselectedColor =
                if (item is NavigationItem.Insert)
                        Orange
                    else
                        MaterialTheme.colors.secondary*/

            BottomNavigationItem(
                icon = {
                        Icon(painterResource(id = iconId), contentDescription = item.icon.description)
                    },
                    selectedContentColor = Orange, //MaterialTheme.colors.onSecondary,
                    unselectedContentColor = Gray, //MaterialTheme.colors.secondary,
                    alwaysShowLabel = false,
                    selected = selectedItem,//currentRoute == item.route.id,
                    //selected = false,
                    onClick = {
                        when (item) {
                            is NavigationItem.Insert -> {
                                item.icon.value = R.drawable.ic_insert_on
                                DialogRouter.navigateTo(Dialog.InsertButtons)
                            }
                            /*is NavigationItem.Settings -> {
                                val dialogMenuDoc = BottomSheetMenu(context, R.style.BottomSheetDialog)
                                dialogMenuDoc.show()
                            }*/
                            else -> {
                                //NavigationItem.Insert.icon.value = R.drawable.ic_insert
                                //viewModel.showInsertButtons(false)
                                //DialogRouter.reset()
                                viewModel.showPanelInsertObj(false)
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