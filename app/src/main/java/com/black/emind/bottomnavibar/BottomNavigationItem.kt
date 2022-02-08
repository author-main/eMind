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


/*enum class Route(val id: String) {
    SEARCH(ID_SEARCH),
    EMIND(ID_EMIND),
    INSERT(ID_INSERT),
    FAVORITE(ID_FAVORITE),
    SETTINGS(ID_SETTINGS)
}*/

enum class NaviItemData(val route: String, @DrawableRes var value: Int, val valueOn: Int? = null, val description: String) {
    SEARCH      (ID_SEARCH  , R.drawable.ic_search,     R.drawable.ic_search_on,     itemsDescription[0]),
    EMIND       (ID_EMIND   , R.drawable.ic_files,      R.drawable.ic_files_on,      itemsDescription[1]),
    INSERT      (ID_INSERT  , R.drawable.ic_insert,     R.drawable.ic_insert_on,     itemsDescription[2]),
    FAVORITE    (ID_FAVORITE, R.drawable.ic_favorite,   R.drawable.ic_favorite_on,   itemsDescription[3]),
    SETTINGS    (ID_SETTINGS, R.drawable.ic_settings,   R.drawable.ic_settings_on,   itemsDescription[4])
}

sealed class NavigationItem(var data: NaviItemData){
    object Search:   NavigationItem(NaviItemData.SEARCH)
    object Emind:    NavigationItem(NaviItemData.EMIND)
    object Insert:   NavigationItem(NaviItemData.INSERT)
    object Favorite: NavigationItem(NaviItemData.FAVORITE)
    object Settings: NavigationItem(NaviItemData.SETTINGS)
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
        NavigationItem.Insert.data.value = R.drawable.ic_insert_on
    else
        NavigationItem.Insert.data.value = R.drawable.ic_insert
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
            val selectedItem = currentRoute == item.data.route
            val iconId =
                if (selectedItem)
                    item.data.valueOn ?: item.data.value
                else
                    item.data.value
           /* val unselectedColor =
                if (item is NavigationItem.Insert)
                        Orange
                    else
                        MaterialTheme.colors.secondary*/

            BottomNavigationItem(
                icon = {
                        Icon(painterResource(id = iconId), contentDescription = item.data.description)
                    },
                    selectedContentColor = Orange, //MaterialTheme.colors.onSecondary,
                    unselectedContentColor = Gray, //MaterialTheme.colors.secondary,
                    alwaysShowLabel = false,
                    selected = selectedItem,//currentRoute == item.route.id,
                    onClick = {
                        when (item) {
                            is NavigationItem.Insert -> {
                                item.data.value = R.drawable.ic_insert_on
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
                                navController.navigate(item.data.route) {
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