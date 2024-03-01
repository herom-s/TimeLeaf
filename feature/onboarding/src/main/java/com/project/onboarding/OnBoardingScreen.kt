package com.project.onboarding

import android.widget.Toast
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.project.designsystem.component.TlHorizontalPager
import com.project.designsystem.theme.TlTheme
import com.project.timeleaf.feature.onboarding.R


@Composable
fun SplashScreen(
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
            ) { Text(stringResource(id = R.string.onboardingSplash_btn_txt)) }
        }
    }
}

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun OnBoardingScreen(
    navController: NavHostController, onBoardingViewModel: OnBoardingViewModel = hiltViewModel(),
) {
    val effect = onBoardingViewModel.uiEffect.collectAsState()
    var shouldShowSplashOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowSplashOnboarding) {
        SplashScreen(onContinueClicked = { shouldShowSplashOnboarding = false })
    } else {
        TlTheme {
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
                    TlHorizontalPager(onNameChanged = {
                        onBoardingViewModel.handleIntent(
                            OnBoardingIntent.NameChanged(it)
                        )
                    }, validateUserName = {
                        onBoardingViewModel.validateUserName(it)
                    }, onGenderChanged = {
                        onBoardingViewModel.handleIntent(
                            OnBoardingIntent.GenderChanged(it)
                        )
                    }, onAgeChanged = {
                        onBoardingViewModel.handleIntent(
                            OnBoardingIntent.AgeChanged(it)
                        )
                    }, validateUserAge = {
                        onBoardingViewModel.validateUserAge(it)
                    }, onLvlPhysicalActivityChanged = {
                        onBoardingViewModel.handleIntent(
                            OnBoardingIntent.LvlPhysicalActivityChanged(
                                it
                            )
                        )
                    }, onHeightChanged = {
                        onBoardingViewModel.handleIntent(
                            OnBoardingIntent.HeightChanged(it)
                        )
                    }, validateUserHeight = {
                        onBoardingViewModel.validateUserHeight(it)
                    }, onWeightChanged = {
                        onBoardingViewModel.handleIntent(
                            OnBoardingIntent.WeightChanged(it)
                        )
                    }, validateUserWeight = { onBoardingViewModel.validateUserWeight(it) },
                        {
                        onBoardingViewModel.handleIntent(
                            OnBoardingIntent.OnBoardingSubmit
                        )
                    })
                }
            }

            when (effect.value) {
                is OnboardingEffect.OnBoardingError -> {
                    val context = LocalContext.current
                    Toast.makeText(
                        context,
                        (effect.value as OnboardingEffect.OnBoardingError).message,
                        Toast.LENGTH_LONG
                    ).show()
                }

                OnboardingEffect.OnBoardingSuccess -> {
                    val context = LocalContext.current
                    Toast.makeText(context, "Success on user register", Toast.LENGTH_LONG).show()
                    navController.popBackStack()
                    navController.navigate("coach_screen")
                }

                OnboardingEffect.ShowLoadingIndicator -> {
                    CircularProgressIndicator()
                }

                else -> {}
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SplashScreenPreview() {
    TlTheme(darkTheme = false, dynamicColor = false) {
        SplashScreen({ mutableStateOf(false) }, Modifier.fillMaxSize())
    }
}