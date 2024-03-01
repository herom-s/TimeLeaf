package com.project.timeleaf.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.onboarding.OnBoardingScreen
import com.project.timeleaf.ui.coach.CoachScreen

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    shouldHideOnboarding: Boolean
) {

    val startDestination = when(shouldHideOnboarding) {
        true -> Screen.Coach.route
        false -> Screen.OnBoarding.route
    }

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