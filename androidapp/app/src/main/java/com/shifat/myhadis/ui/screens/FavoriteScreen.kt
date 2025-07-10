package com.shifat.myhadis.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.shifat.myhadis.model.Hadis
import com.shifat.myhadis.ui.common.BottomBar
import com.shifat.myhadis.ui.common.HadisCard
import com.shifat.myhadis.ui.common.TopBar
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shifat.myhadis.ui.common.HadisDialog


@Composable
fun FavoriteScreen(navController: NavHostController) {
    val viewModel: HadisViewModel = hiltViewModel()

    val hadisList:  State<List<Hadis>> = viewModel.favoriteHadisList.collectAsState()

    val context = LocalContext.current
    val listState = rememberLazyListState()
    val topBarVisibleState = remember { mutableStateOf(true) }

    var selectedHadis by remember { mutableStateOf<Hadis?>(null)}

    LaunchedEffect(listState.firstVisibleItemScrollOffset) {
        topBarVisibleState.value = listState.firstVisibleItemScrollOffset <= 0
    }

    Scaffold(
        topBar = {
            TopBar(topBarVisibleState, "Favorite Hadiths")
        },
        bottomBar = {
            BottomBar(navController = navController, currentScreen = Screen.FavoriteScreen.name)
        }
    ) { it ->
        LazyColumn(
            state = listState,
            modifier = Modifier.padding(it)
        ) {
            items(hadisList.value.size) { index ->
                HadisCard(hadis = hadisList.value[index]) {
                    selectedHadis = it
                }
            }
        }
    }

    if (selectedHadis != null) {
        HadisDialog(
            hadis = selectedHadis!!,
            onDismiss = { selectedHadis = null },
            onFavoriteClick = {

                viewModel.toggleFavorite(it)
                viewModel.isFavortie(it)
                viewModel.updateFavoriteHadis()
            }
        )
    }
}