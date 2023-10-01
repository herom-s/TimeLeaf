package com.project.timeleaf.ui.onboarding.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId

@ExperimentalMaterial3Api
@Composable
fun PagerInputDatePicker(
    onValueChange: (String) -> Unit,
) {
    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Input,
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    )
    DatePicker(
        modifier = Modifier.fillMaxWidth(),
        state = datePickerState,
        showModeToggle = false,
    )

    val age = datePickerState.selectedDateMillis?.let {
        val selectedDate = Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
        val currentDate = LocalDate.now()
        Period.between(selectedDate, currentDate)
    }

    if (age != null) {
        onValueChange(age.years.toString())
    }
}

@ExperimentalMaterial3Api
@Preview(name = "PagerInputDatePicker")
@Composable
private fun PreviewPagerInputDatePicker() {
    PagerInputDatePicker {}
}