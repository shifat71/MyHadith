package com.shifat.myhadis.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.navigation.NavHostController
import com.shifat.myhadis.ui.screens.Screen


@Composable
fun BottomBar(navController: NavHostController, currentScreen: String) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home", style = MaterialTheme.typography.labelMedium) },
            selected = currentScreen == Screen.HomeScreen.name,
            onClick = {
                navController.navigate(Screen.HomeScreen.name)
            },
            modifier = if(currentScreen == Screen.HomeScreen.name) Modifier.alpha(.4f) else Modifier.alpha(1f)
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Favorite , contentDescription = "Favorite") },
            label = { Text("Favorite", style = MaterialTheme.typography.labelMedium) },
            selected = currentScreen == Screen.FavoriteScreen.name,
            onClick = {

                navController.navigate(Screen.FavoriteScreen.name)
            },
            modifier = if(currentScreen == Screen.FavoriteScreen.name) Modifier.alpha(.4f) else Modifier.alpha(1f)
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.AccountBox  , contentDescription = "Contacts") },
            label = { Text("Contacts", style = MaterialTheme.typography.labelMedium) },
            selected = currentScreen == Screen.ContactsScreen.name,
            onClick = {
                navController.navigate(Screen.ContactsScreen.name)
            },
            modifier = if(currentScreen == Screen.ContactsScreen.name) Modifier.alpha(.4f) else Modifier.alpha(1f)
        )


    }
}