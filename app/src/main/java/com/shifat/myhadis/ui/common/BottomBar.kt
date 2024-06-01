package com.shifat.myhadis.ui.common

import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp


@Composable
fun BottomBar() {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home", style = MaterialTheme.typography.labelMedium) },
            selected = false,
            onClick = {}
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Favorite , contentDescription = "Favorite") },
            label = { Text("Favorite", style = MaterialTheme.typography.labelMedium) },
            selected = false,
            onClick = {}
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.AccountBox  , contentDescription = "Contacts") },
            label = { Text("Contacts", style = MaterialTheme.typography.labelMedium) },
            selected = true,
            onClick = {}
        )


    }
}