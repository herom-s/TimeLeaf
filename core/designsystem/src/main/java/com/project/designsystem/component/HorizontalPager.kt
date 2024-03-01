package com.project.designsystem.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.designsystem.theme.TlTheme
import com.project.timeleaf.core.designsystem.R
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun TlHorizontalPager(
    onNameChanged: (String) -> Unit,
    validateUserName: (String) -> Boolean,
    onGenderChanged: (String) -> Unit,
    onAgeChanged: (String) -> Unit,
    validateUserAge: (String) -> Boolean,
    onLvlPhysicalActivityChanged: (String) -> Unit,
    onHeightChanged: (String) -> Unit,
    validateUserHeight: (String) -> Boolean,
    onWeightChanged: (String) -> Unit,
    validateUserWeight: (String) -> Boolean,
    onFinalize: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        initialPage = 0, initialPageOffsetFraction = 0f
    ) {
        5
    }
    val headers = stringArrayResource(id = R.array.onboardingPagerHeaders_txt)
    val label = stringArrayResource(id = R.array.onboardingPagerLabels_txt)

    Surface(color = MaterialTheme.colorScheme.background) {
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.CenterVertically,
            userScrollEnabled = false
        ) { position ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PagerText(headers[position])
                Column(modifier = Modifier.padding(6.dp)) {
                    when (position) {
                        0 -> {
                            PagerInputTextField(
                                label = label[0],
                                onValueChange = onNameChanged,
                                validateInput = validateUserName
                            )
                        }

                        1 -> {
                            PagerInputDropDown(onGenderChanged)
                        }

                        2 -> {
                            PagerInputDatePicker(onAgeChanged, validateUserAge)
                        }

                        3 -> {
                            PagerInputSlider(onLvlPhysicalActivityChanged)
                        }

                        4 -> {
                            Column(modifier = Modifier.padding(6.dp)) {
                                PagerInputTextField(
                                    label = label[1],
                                    onValueChange = onHeightChanged,
                                    validateInput = validateUserHeight
                                )
                                PagerInputTextField(
                                    label = label[2],
                                    onValueChange = onWeightChanged,
                                    validateInput = validateUserWeight
                                )
                            }
                        }

                        else -> {}
                    }
                }
                Row {
                    if (position >= 1) {
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.scrollToPage(pagerState.currentPage - 1)
                                }
                            }, modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(16.dp)
                        ) {
                            Text(text = stringResource(id = R.string.onboardingPagerBack_btn_txt))
                        }
                    }
                    Button(
                        onClick = {
                            if (position + 1 < headers.size) coroutineScope.launch {
                                pagerState.scrollToPage(pagerState.currentPage + 1)
                            } else {
                                onFinalize()
                            }
                        }, modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = if (position + 1 < headers.size) stringResource(id = R.string.onboardingPagerContinue_btn_txt) else stringResource(
                                id = R.string.onboardingPagerFinalize_btn_txt
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PagerText(title: String) {
    Text(
        text = title,
        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Composable
fun PagerInputTextField(
    label: String,
    onValueChange: (String) -> Unit,
    validateInput: (String) -> Boolean,
) {
    val textValue = rememberSaveable { mutableStateOf("") }

    TextField(value = textValue.value,
        onValueChange = {
            textValue.value = it
            onValueChange(it)
        },
        label = { Text(text = label) },
        isError = textValue.value.isNotEmpty() && !validateInput(textValue.value))
}

@Composable
fun PagerInputSlider(
    onValueChange: (String) -> Unit = {},
) {
    val valueArray = arrayOf("1.2", "1.375", "1.55", "1.725", "1.9")
    val valueHeaderArray = stringArrayResource(id = R.array.onboardingPagerFourthValueHeaders_txt)
    val valueTipArray = stringArrayResource(id = R.array.onboardingPagerFourthValueTips_txt)
    var sliderPosition: Float by rememberSaveable { mutableFloatStateOf(0.0f) }

    onValueChange(valueArray[sliderPosition.toInt()])

    Column {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = valueHeaderArray[sliderPosition.toInt()],
            style = MaterialTheme.typography.headlineMedium
        )

        Slider(modifier = Modifier
            .width(200.dp)
            .align(Alignment.CenterHorizontally),
            steps = 3,
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0.0f..4.0f,
            onValueChangeFinished = {
                onValueChange(valueArray[sliderPosition.toInt()])
            })
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .height(40.dp),
            text = valueTipArray[sliderPosition.toInt()],
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun PagerInputDropDown(
    onValueChange: (String) -> Unit = {},
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val dropDownText = stringArrayResource(id = R.array.onboardingPagerSecondDropDown_txt)
    var selectedItem by rememberSaveable { mutableStateOf(dropDownText[0]) }

    onValueChange(dropDownText[0])

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
        expanded = it
    }) {

        TextField(
            modifier = Modifier.menuAnchor(),
            value = selectedItem,
            onValueChange = { onValueChange(it) },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            dropDownText.forEach { selectedOption ->
                if (selectedItem != selectedOption) {
                    DropdownMenuItem(text = { Text(text = selectedOption) }, onClick = {
                        selectedItem = selectedOption
                        expanded = false
                    })
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun PagerInputDatePicker(
    onValueChange: (String) -> Unit,
    validateInput: (String) -> Boolean,
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
        validateInput(age.years.toString())
    }
}

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Preview(name = "TlHorizontalPager")
@Composable
private fun PreviewTlHorizontalPager() {
    TlTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TlHorizontalPager({}, { false }, {}, {}, { false }, {}, {}, { false }, {}, { false }) {}
        }
    }
}