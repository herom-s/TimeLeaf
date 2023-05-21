package com.project.timeleaf.navigation

sealed class Screen(val route: String) {
    object OnBoarding : Screen("onboarding_screen")
    object Profile : Screen("profile_screen")
    object Coach : Screen("coach_screen")
}
