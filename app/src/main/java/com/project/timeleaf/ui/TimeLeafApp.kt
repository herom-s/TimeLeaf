package com.project.timeleaf.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.project.timeleaf.navigation.SetupNavGraph
import com.project.timeleaf.ui.splash.SplashViewModel
import com.project.timeleaf.ui.theme.TimeLeafTheme

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun TimeLeafApp(splashViewModel: SplashViewModel) {
    TimeLeafTheme {
        val screen by splashViewModel.startDestination
        val navController = rememberNavController()
        SetupNavGraph(navController = navController, startDestination = screen)
    }
}