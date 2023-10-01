package com.project.timeleaf.ui.onboarding

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.project.timeleaf.R
import com.project.timeleaf.navigation.Screen
import com.project.timeleaf.ui.onboarding.components.OnBoardingPager
import com.project.timeleaf.ui.theme.TimeLeafTheme
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun OnBoardingScreen(
    navController: NavHostController, onBoardingViewModel: OnBoardingViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    var shouldShowSplashOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowSplashOnboarding) {
            OnboardingSplashScreen(onContinueClicked = { shouldShowSplashOnboarding = false })
    } else {
        Surface(color = MaterialTheme.colorScheme.background) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.inversePrimary
                            )
                        )
                    )
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
                    OnBoardingPager(onNameChanged = {
                        onBoardingViewModel.onEvent(
                            OnBoardingUiEvent.NameChanged(
                                it
                            )
                        )
                    },
                        onGenderChanged = {
                            onBoardingViewModel.onEvent(
                                OnBoardingUiEvent.GenderChanged(it)
                            )
                        },
                        onAgeChanged = { onBoardingViewModel.onEvent(OnBoardingUiEvent.AgeChanged(it)) },
                        onLvlPhysicalActivityChanged = {
                            onBoardingViewModel.onEvent(
                                OnBoardingUiEvent.LvlPhysicalActivityChanged(
                                    it
                                )
                            )
                        },
                        onHeightChanged = {
                            onBoardingViewModel.onEvent(
                                OnBoardingUiEvent.HeightChanged(it)
                            )
                        },
                        onWeightChanged = {
                            onBoardingViewModel.onEvent(
                                OnBoardingUiEvent.WeightChanged(it)
                            )
                        },
                        onFinalize = {
                            coroutineScope.launch {
                                onBoardingViewModel.onEvent(
                                    OnBoardingUiEvent.OnBoardingCompleted(
                                        true
                                    )
                                )
                                onBoardingViewModel.saveUser()
                                navController.popBackStack()
                                navController.navigate(Screen.Coach.route)
                            }
                        })
                }
            }
        }
    }
}

@Composable
fun OnboardingSplashScreen(
    onContinueClicked: () -> Unit, modifier: Modifier = Modifier,
) {
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
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.6f)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(id = R.string.onboardingSplashHeader_txt),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Button(
                    modifier = Modifier.padding(vertical = 24.dp), onClick = onContinueClicked
                ) {
                    Text(stringResource(id = R.string.onboardingSplash_btn_txt))
                }
            }
        }
}

@Composable
@Preview(showBackground = true)
fun OnboardingSplashScreenPreview() {
    TimeLeafTheme(darkTheme = false, dynamicColor = false) {
        OnboardingSplashScreen({ mutableStateOf(false) }, Modifier.fillMaxSize())
    }
}