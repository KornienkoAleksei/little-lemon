package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation (navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences by lazy {
        context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    }
    val userLogin = sharedPreferences.getBoolean("Login", false);
    NavHost(
        navController = navController,
        startDestination = if (userLogin) Home.route else Onboarding.route)
    {
        composable(Onboarding.route){
            Onboarding(navController = navController)
        }
        composable(Home.route){
            Home(navController = navController)
        }
        composable(Profile.route){
            Profile(navController = navController)
        }
    }
}