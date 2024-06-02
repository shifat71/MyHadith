package com.shifat.myhadis.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shifat.myhadis.model.Hadis
import com.shifat.myhadis.ui.common.BottomBar
import com.shifat.myhadis.ui.common.HadisCard
import com.shifat.myhadis.ui.common.TopBar
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController


@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val authViewModel: AuthViewModel = hiltViewModel()

    val hadisList:  State<List<Hadis>> = viewModel.hadisList.collectAsState()

    val isOtpSuccess = authViewModel.isOtpSuccess.collectAsState().value
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val topBarVisibleState = remember { mutableStateOf(true) }

    var selectedHadis by remember { mutableStateOf<Hadis?>(null)}

    LaunchedEffect(listState.firstVisibleItemScrollOffset) {
        topBarVisibleState.value = listState.firstVisibleItemScrollOffset <= 0
    }
    if (isOtpSuccess) {
        Toast.makeText(context, "You are subscribed to the service for 30 days. Welcome!", Toast.LENGTH_SHORT).show()
    }
    Scaffold(
        topBar = {
            TopBar(topBarVisibleState, "Home")
        },
        bottomBar = {
            BottomBar(navController = navController, currentScreen = Screen.HomeScreen.name)
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
            onFavoriteClick = { viewModel.toggleFavorite(it) }
        )
    }
}
@Composable
fun HadisDialog(
    hadis: Hadis, onDismiss: () -> Unit,
    onFavoriteClick: (Hadis) -> Unit
) {
    val viewModel: HomeViewModel = viewModel()
    var isFavorite by remember { mutableStateOf(viewModel.isFavortie(hadis)) }
    val listState = rememberLazyListState()
    val topBarVisibleState = remember { mutableStateOf(true) }

    Dialog(onDismissRequest = onDismiss) {
       Box(contentAlignment = Alignment.Center,
           modifier = Modifier.fillMaxSize()){
           Scaffold(
               topBar = {
                   TopBar(topBarVisibleState = topBarVisibleState , ScreenName = "Send Hadith" )},
               floatingActionButton = {
                   Row( horizontalArrangement = Arrangement.Center,
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(start = 50.dp),

                   ) {
                       FloatingActionButton(onClick = {
                         viewModel.sendHadis(hadis)
                           viewModel.resetError()
                       }) {
                           Icon(Icons.Filled.Send, contentDescription = "Send")
                       }

                   }
               }
           ) { it ->

               Surface {
                   LazyColumn(
                       modifier = Modifier
                           .padding(it)
                           .padding(15.dp),
                          state = listState
                   ) {
                       item {

                           Row(
                               modifier = Modifier.fillMaxWidth(),
                               horizontalArrangement = Arrangement.SpaceBetween
                           ) {

                               Text(
                                   modifier = Modifier
                                       .weight(0.95f)
                                       .align(Alignment.CenterVertically),
                                   text = hadis.title,
                                   style = MaterialTheme.typography.titleLarge,
                                   color = MaterialTheme.colorScheme.surfaceTint
                               )

                               IconButton(
                                   modifier = Modifier.padding(
                                       5.dp),
                                   onClick = {
                                       onFavoriteClick(hadis)
                                       isFavorite = viewModel.isFavortie(hadis)
                                   }) {
                                   Icon(
                                       if (isFavorite) Icons.Filled.Favorite
                                       else Icons.Outlined.FavoriteBorder, contentDescription = null
                                   )
                               }
                           }
                           Spacer(modifier = Modifier.height(10.dp))
                           Text(
                               text = hadis.description,
                               style = MaterialTheme.typography.bodyLarge,
                               color = MaterialTheme.colorScheme.onSurfaceVariant
                           )

                           Spacer(modifier = Modifier.height(10.dp))
                           Text(
                               text = "Book: ${hadis.book}",
                               style = MaterialTheme.typography.titleSmall,
                               color = MaterialTheme.colorScheme.onBackground
                           )

                           Text(
                               text = "Rabbi: ${hadis.rabi}",
                               style = MaterialTheme.typography.titleSmall,
                               color = MaterialTheme.colorScheme.onBackground
                           )
                           Text(
                               text = "Level: ${hadis.level}",
                               style = MaterialTheme.typography.titleSmall,
                               color = MaterialTheme.colorScheme.onBackground
                           )
                       }

                   }
               }

           }
       }
    }
}