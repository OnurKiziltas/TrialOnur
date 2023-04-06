package com.example.trialonur.features.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.trialonur.features.screen.home.HomeScreen
import com.example.trialonur.features.screen.secondscreen.SecondScreen
import com.example.trialonur.util.Utility.toJson


@Composable
fun NavGraph(startDestination: String = NavScreen.Home.route){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavHost(navController = navController, startDestination = startDestination){

        composable(NavScreen.Home.route){
            HomeScreen(
                hiltViewModel(),
            onNavigateSecondScreen = {
                navController.navigate(NavScreen.SecondScreen.route.plus("?satelliteList=${it.toJson()}"))
            })
        }

        composable(NavScreen.SecondScreen.route.plus("?satelliteList={satelliteList}")){
            SecondScreen(
                hiltViewModel(),
                onNavigateHomeScreen = {
                    navController.popBackStack()
                })
        }
    }
}