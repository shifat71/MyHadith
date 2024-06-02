package com.shifat.myhadis

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shifat.myhadis.ui.screens.ContactScreen
import com.shifat.myhadis.ui.screens.FavoriteScreen
import com.shifat.myhadis.ui.screens.HomeScreen
import com.shifat.myhadis.ui.screens.OtpScreen
import com.shifat.myhadis.ui.screens.Screen

@Composable
fun App(){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ContactsScreen.name ){

        composable(Screen.Login.name){
            LoginScreen(navController = navController)
        }

        composable(Screen.OtpScreen.name){
            OtpScreen(navController = navController)
        }
        composable(Screen.HomeScreen.name){
            HomeScreen(navController = navController)
        }

        composable(Screen.FavoriteScreen.name){
            FavoriteScreen(navController = navController)
        }

        composable(Screen.ContactsScreen.name){
            ContactScreen(navController = navController)
        }
    }

}