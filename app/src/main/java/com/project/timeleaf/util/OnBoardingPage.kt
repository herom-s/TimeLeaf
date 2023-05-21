package com.project.timeleaf.util


sealed class OnBoardingPage(
    val title: String
) {
    object First : OnBoardingPage("Hey, What’s your name?")

    object Second : OnBoardingPage("What’s your gender?")

    object Third : OnBoardingPage("What’s your age?")

    object Fourth : OnBoardingPage("What’s your activity level?")

    object Fifth : OnBoardingPage("What’s your height and weight?")

}