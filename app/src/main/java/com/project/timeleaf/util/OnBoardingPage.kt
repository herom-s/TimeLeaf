package com.project.timeleaf.util

import android.icu.util.Calendar
import com.project.timeleaf.R


sealed class OnBoardingPage(
    val title: Int,
    val type: Type,
    val LabelArray: Array<Int>
) {
    enum class Type {
        TextField, Slider, DatePicker, DropDown
    }
    object First : OnBoardingPage(R.string.onboardingPagerFirstHeader_txt, Type.TextField, arrayOf(R.string.onboardingPagerFirstPlaceholder_txt))

    object Second : OnBoardingPage(R.string.onboardingPagerSecondHeader_txt,Type.DropDown, arrayOf(R.string.onboardingPagerSecondDropDown1_txt, R.string.onboardingPagerSecondDropDown2_txt))

    object Third : OnBoardingPage(R.string.onboardingPagerThirdHeader_txt, Type.DatePicker, emptyArray())

    object Fourth : OnBoardingPage(R.string.onboardingPagerFourthHeader_txt, Type.Slider, emptyArray())

    object Fifth : OnBoardingPage(R.string.onboardingPagerFifthHeader_txt, Type.TextField, arrayOf(R.string.onboardingPagerFifthPlaceholder1_txt, R.string.onboardingPagerFifthPlaceholder2_txt))

    fun calculateAge(date: Long): Int {
        val dob: Calendar = Calendar.getInstance()
        dob.timeInMillis = date
        val today: Calendar = Calendar.getInstance()
        var age: Int = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
        if (today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            age--
        }
        return age
    }
}