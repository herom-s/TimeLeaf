package com.project.timeleaf.ui.onboarding

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.project.timeleaf.R
import com.project.timeleaf.navigation.Screen
import com.project.timeleaf.ui.theme.TimeLeafTheme
import com.project.timeleaf.util.OnBoardingPage
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun OnBoardingScreen(
    navController: NavHostController, onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
) {
    var shouldShowSplashOnboarding by rememberSaveable { mutableStateOf(true) }
    var shouldShowBackButton by rememberSaveable { mutableStateOf(false) }
    var shouldShowContinueButton by rememberSaveable { mutableStateOf(true) }
    var shouldShowFinalizeButton by rememberSaveable { mutableStateOf(false) }

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

    if (shouldShowSplashOnboarding) {
        OnboardingSplashScreen(onContinueClicked = { shouldShowSplashOnboarding = false })
    } else {
        Column {
            HorizontalPager(
                modifier = Modifier.weight(1f),
                state = pagerState,
                verticalAlignment = Alignment.Top
            ) { position ->
                PagerScreen(onBoardingPage = pages[position])
            }
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
                    onBoardingViewModel.saveOnBoardingState(completed = true)
                    navController.popBackStack()
                    navController.navigate(Screen.Coach.route)
                })
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        if (shouldShowBackButton) {
            // Back button
            OutlinedButton(
                onClick = onBackClick
            ) {
                Text(text = "Back")
            }
        }
        if (shouldShowContinueButton) {
            // Continue Button
            Button(
                onClick = onContinueClick
            ) {
                Text(text = "Continue")
            }
        }

        if (shouldShowFinalizeButton) {
            // Finalize Button
            Button(
                onClick = onFinalizeClick
            ) {
                Text(text = "Finalize")
            }
        }
    }
}

@Composable
fun OnboardingSplashScreen(
    onContinueClicked: () -> Unit, modifier: Modifier = Modifier
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
                    stringResource(id = R.string.onboardingSplash_txt),
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
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    val colorList = listOf(
        MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.inversePrimary
    )
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
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = onBoardingPage.title,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
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

@Composable
@Preview(showBackground = true)
fun FirstOnBoardingScreenPreview() {
    TimeLeafTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            PagerScreen(onBoardingPage = OnBoardingPage.First)
        }
    }
}