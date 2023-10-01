package com.project.timeleaf.ui.onboarding.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.timeleaf.R
import com.project.timeleaf.ui.theme.TimeLeafTheme
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun OnBoardingPager(
    onNameChanged: (String) -> Unit,
    onGenderChanged: (String) -> Unit,
    onAgeChanged: (String) -> Unit,
    onLvlPhysicalActivityChanged: (String) -> Unit,
    onHeightChanged: (String) -> Unit,
    onWeightChanged: (String) -> Unit,
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
                            PagerInputTextField(label = label[0], onValueChange = onNameChanged)
                        }

                        1 -> {
                            PagerInputDropDown(onGenderChanged)
                        }

                        2 -> {
                            PagerInputDatePicker(onAgeChanged)
                        }

                        3 -> {
                            PagerInputSlider(onLvlPhysicalActivityChanged)
                        }

                        4 -> {
                            PagerInputTextField(
                                label = label[1], onValueChange = onHeightChanged
                            )
                            PagerInputTextField(
                                label = label[2], onValueChange = onWeightChanged
                            )
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

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Preview(name = "OnboardingPager")
@Composable
private fun PreviewOnboardingPager() {
    TimeLeafTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            OnBoardingPager({}, {}, {}, {}, {}, {}, {})
        }
    }
}