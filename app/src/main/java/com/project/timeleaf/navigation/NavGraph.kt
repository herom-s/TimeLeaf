package com.project.timeleaf.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.timeleaf.ui.onboarding.OnBoardingScreen
import com.project.timeleaf.ui.coach.CoachScreen

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.OnBoarding.route) {
            OnBoardingScreen(navController = navController)
        }
        composable(route = Screen.Coach.route) {
            CoachScreen()
        }
    }
}