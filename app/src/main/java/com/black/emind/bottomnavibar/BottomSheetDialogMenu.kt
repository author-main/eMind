package com.black.emind.bottomnavibar

import android.view.MenuItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.black.emind.MainViewModel
import com.black.emind.log
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun BottomSheetDialogMenu(){
/*    val viewModel = viewModel<MainViewModel>()
    val isShowMenu by viewModel.isShowMenu.observeAsState()*/
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

   /* val dismissState = rememberDismissState(DismissValue.DismissedToStart) { value ->
        log(value.toString())
        true
    }*/
    //log(dismissState.toString())
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetShape = AbsoluteRoundedCornerShape(5,5,0,0),
        //sheetShape = RoundedCornerShape(50),
        sheetState = state,
        sheetContent = {
            LazyColumn(        modifier = Modifier.height(320.dp)) {
                items(50) {
                    ListItem(
                        text = { Text("Item $it") },
                        icon = {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    )
                }
            }
        }

    ) {

        scope.launch {

                state.show()
        }

    }/*{
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Rest of the UI")
            Spacer(Modifier.height(20.dp))
            Button(onClick = { scope.launch { state.show() } }) {
                Text("Click to show sheet")
            }
        }
    }*/


}

/*
Button(onClick = {
    coroutineScope.launch {

        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
            bottomSheetScaffoldState.bottomSheetState.expand()
        } else {
            bottomSheetScaffoldState.bottomSheetState.collapse()
        }
    }
}) {
    Text(text = "Expand/Collapse Bottom Sheet")
}*/