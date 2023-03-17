package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation (navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Onboarding.route)
    {
        composable(Onboarding.route){
            Onboarding()
        }
        composable(Home.route){
            Home()
        }
        composable(Profile.route){
            Profile()
        }
    }
}

/*
Determine the startDestination. If user data is stored in shared preferences,
then the start destination is Onboarding, otherwise, the start destination is Home.
 */