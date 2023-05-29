package com.project.timeleaf.ui.onboarding

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.project.timeleaf.R
import com.project.timeleaf.navigation.Screen
import com.project.timeleaf.ui.state.UserUiState
import com.project.timeleaf.ui.theme.TimeLeafTheme
import com.project.timeleaf.util.OnBoardingPage
import kotlinx.coroutines.launch
import java.time.Instant

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun OnBoardingScreen(
    navController: NavHostController, onBoardingViewModel: OnBoardingViewModel = hiltViewModel(),
) {
    var shouldShowSplashOnboarding by rememberSaveable { mutableStateOf(true) }
    var shouldShowBackButton by rememberSaveable { mutableStateOf(false) }
    var shouldShowContinueButton by rememberSaveable { mutableStateOf(true) }
    var shouldShowFinalizeButton by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,
        OnBoardingPage.Fourth,
        OnBoardingPage.Fifth
    )
    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        initialPage = 0, initialPageOffsetFraction = 0f
    ) {
        5
    }

    val colorList = listOf(
        MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.inversePrimary
    )

    if (shouldShowSplashOnboarding) {
        OnboardingSplashScreen(onContinueClicked = { shouldShowSplashOnboarding = false })
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.horizontalGradient(colorList))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.9f)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp))
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly

            ) {
                TopSection(onBoardingViewModel, pages, pagerState)
                BottomSection(shouldShowBackButton,
                    shouldShowContinueButton,
                    shouldShowFinalizeButton,
                    onBackClick = {
                        if (pagerState.currentPage + 1 > 1) scope.launch {
                            pagerState.scrollToPage(pagerState.currentPage - 1)
                            if (pagerState.currentPage == 0) {
                                shouldShowBackButton = false
                            }
                        }

                        if (shouldShowFinalizeButton) {
                            shouldShowFinalizeButton = false
                            shouldShowContinueButton = true
                        }
                    },
                    onContinueClick = {
                        if (pagerState.currentPage + 1 < pages.size) scope.launch {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                            if (!shouldShowBackButton) {
                                shouldShowBackButton = true
                            }
                            if (pagerState.currentPage + 1 == pages.size) {
                                if (!shouldShowFinalizeButton) {
                                    shouldShowFinalizeButton = true
                                    shouldShowContinueButton = false
                                }
                            }
                        }
                    },
                    onFinalizeClick = {
                        coroutineScope.launch {
                            onBoardingViewModel.saveOnBoardingState(completed = true)
                            onBoardingViewModel.saveUser()
                            navController.popBackStack()
                            navController.navigate(Screen.Coach.route)
                        }
                    })
            }
        }
    }


}

@Composable
fun PagerText(onBoardingPage: OnBoardingPage) {
    Text(
        text = stringResource(id = onBoardingPage.title),
        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@ExperimentalMaterial3Api
@Composable
fun PagerInputMethod(
    userUiState: UserUiState,
    onValueChange: (UserUiState) -> Unit = {},
    onBoardingPage: OnBoardingPage,
) {
    if (onBoardingPage.type == OnBoardingPage.Type.TextField) {
        if (onBoardingPage.LabelArray[0] == R.string.onboardingPagerFirstPlaceholder_txt) {
            TextField(value = userUiState.name,
                onValueChange = { onValueChange(userUiState.copy(name = it)) },
                label = { Text(text = stringResource(id = onBoardingPage.LabelArray[0])) },
                placeholder = { Text(text = stringResource(id = onBoardingPage.LabelArray[0])) })
        } else if (onBoardingPage.LabelArray[0] == R.string.onboardingPagerFifthPlaceholder1_txt && onBoardingPage.LabelArray[1] == R.string.onboardingPagerFifthPlaceholder2_txt) {

            TextField(
                value = userUiState.height,
                onValueChange = { onValueChange(userUiState.copy(height = it)) },
                label = { Text(text = stringResource(id = onBoardingPage.LabelArray[0])) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            TextField(
                value = userUiState.weight,
                onValueChange = { onValueChange(userUiState.copy(weight = it)) },
                label = { Text(text = stringResource(id = onBoardingPage.LabelArray[1])) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
        }
    } else if (onBoardingPage.type == OnBoardingPage.Type.DropDown) {
        var expanded by remember { mutableStateOf(false) }
        val helperText = arrayOf(
            stringResource(id = onBoardingPage.LabelArray[0]),
            stringResource(id = onBoardingPage.LabelArray[1])
        )
        var selectedItem by remember { mutableStateOf(helperText[0]) }

        onValueChange(userUiState.copy(gender = helperText[0]))

        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
            expanded = it
        }) {

            TextField(
                modifier = Modifier.menuAnchor(),
                value = selectedItem,
                onValueChange = { onValueChange(userUiState.copy(gender = it)) },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )

            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                helperText.forEach { selectedOption ->
                    if (selectedItem != selectedOption) {
                        DropdownMenuItem(text = { Text(text = selectedOption) }, onClick = {
                            selectedItem = selectedOption
                            expanded = false
                        })
                    }
                }
            }
        }
    } else if (onBoardingPage.type == OnBoardingPage.Type.DatePicker) {

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
            onBoardingPage.calculateAge(it)
        }
        onValueChange(userUiState.copy(age = age.toString()))
    } else if (onBoardingPage.type == OnBoardingPage.Type.Slider) {
        val valueArray = arrayOf("1.2", "1.375", "1.55", "1.725", "1.9")
        val valueHeaderArray = arrayOf(
            R.string.onboardingPagerFourthValueHeader1_txt,
            R.string.onboardingPagerFourthValueHeader2_txt,
            R.string.onboardingPagerFourthValueHeader3_txt,
            R.string.onboardingPagerFourthValueHeader4_txt,
            R.string.onboardingPagerFourthValueHeader5_txt
        )
        val valueTipArray = arrayOf(
            R.string.onboardingPagerFourthValueTip1_txt,
            R.string.onboardingPagerFourthValueTip2_txt,
            R.string.onboardingPagerFourthValueTip3_txt,
            R.string.onboardingPagerFourthValueTip4_txt,
            R.string.onboardingPagerFourthValueTip5_txt
        )
        var sliderPosition by remember { mutableStateOf(0.0f) }

        onValueChange(userUiState.copy(lvl_physical_activity = valueArray[sliderPosition.toInt()]))

        Text(
            text = stringResource(id = valueHeaderArray[sliderPosition.toInt()]),
            style = MaterialTheme.typography.headlineMedium
        )

        Slider(steps = 3,
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0.0f..4.0f,
            onValueChangeFinished = {
                onValueChange(userUiState.copy(lvl_physical_activity = valueArray[sliderPosition.toInt()]))
            })

        Text(
            text = stringResource(id = valueTipArray[sliderPosition.toInt()]),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun TopSection(
    onBoardingViewModel: OnBoardingViewModel, pages: List<OnBoardingPage>, pagerState: PagerState,
) {

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
            PagerText(onBoardingPage = pages[position])
            PagerInputMethod(
                userUiState = onBoardingViewModel.userUiState,
                onValueChange = onBoardingViewModel::updateUiState,
                onBoardingPage = pages[position]
            )
        }
    }
}

@Composable
fun BottomSection(
    shouldShowBackButton: Boolean,
    shouldShowContinueButton: Boolean,
    shouldShowFinalizeButton: Boolean,
    onBackClick: () -> Unit = {},
    onContinueClick: () -> Unit = {},
    onFinalizeClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        if (shouldShowBackButton) {
            // Back button
            OutlinedButton(
                onClick = onBackClick
            ) {
                Text(text = stringResource(id = R.string.onboardingPagerBack_btn_txt))
            }
        }
        if (shouldShowContinueButton) {
            // Continue Button
            Button(
                onClick = onContinueClick
            ) {
                Text(text = stringResource(id = R.string.onboardingPagerContinue_btn_txt))
            }
        }

        if (shouldShowFinalizeButton) {
            // Finalize Button
            Button(
                onClick = onFinalizeClick
            ) {
                Text(text = stringResource(id = R.string.onboardingPagerFinalize_btn_txt))
            }
        }
    }
}

@Composable
fun OnboardingSplashScreen(
    onContinueClicked: () -> Unit, modifier: Modifier = Modifier,
) {
    Surface {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.img_onboarding_background),
                contentDescription = null,
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = modifier
                    .matchParentSize()
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(id = R.string.onboardingSplashHeader_txt),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Button(
                    modifier = Modifier.padding(vertical = 24.dp), onClick = onContinueClicked
                ) {
                    Text(stringResource(id = R.string.onboardingSplash_btn_txt))
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun OnboardingSplashScreenPreview() {
    TimeLeafTheme {
        OnboardingSplashScreen({ mutableStateOf(false) }, Modifier.fillMaxSize())
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
@Preview(showBackground = true)
fun OnBoardingPagerPreview() {
    val pagerState = rememberPagerState(
        initialPage = 0, initialPageOffsetFraction = 0f
    ) {
        5
    }
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,
        OnBoardingPage.Fourth,
        OnBoardingPage.Fifth
    )
    TimeLeafTheme {
        TopSection(onBoardingViewModel = hiltViewModel(), pages = pages, pagerState = pagerState)
    }
}