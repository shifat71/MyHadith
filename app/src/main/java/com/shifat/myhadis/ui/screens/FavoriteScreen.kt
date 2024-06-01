package com.shifat.myhadis.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shifat.myhadis.model.Hadis
import com.shifat.myhadis.ui.common.BottomBar
import com.shifat.myhadis.ui.common.HadisCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true, showBackground = true)
fun FavoriteScreen(navController: NavHostController) {
    val viewModel: FavoriteViewModel = hiltViewModel()
    val hadisList:  State<List<Hadis>> = viewModel.favoriteHadisList.collectAsState()

    val listState = rememberLazyListState()
    val topBarVisibleState = remember { mutableStateOf(true) }

    LaunchedEffect(listState.firstVisibleItemScrollOffset) {
        topBarVisibleState.value = listState.firstVisibleItemScrollOffset <= 0
    }

    Scaffold(
        topBar = {
            AnimatedVisibility(visible = topBarVisibleState.value) {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text("Favorite Hadith")
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
                    modifier = Modifier.height(75.dp)
                )
            }
        },
        bottomBar = {
            BottomBar()
        }
    ) { it ->
        LazyColumn(
            state = listState,
            modifier = Modifier.padding(it)
        ) {
            items(hadisList.value.size) { index ->
                HadisCard(hadis = hadisList.value[index]) {

                }
            }
        }
    }
}
