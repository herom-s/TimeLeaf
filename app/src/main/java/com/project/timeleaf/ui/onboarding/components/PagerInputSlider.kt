package com.project.timeleaf.ui.onboarding.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.timeleaf.R
import com.project.timeleaf.ui.theme.TimeLeafTheme

@ExperimentalMaterial3Api
@Composable
fun PagerInputSlider(
    onValueChange: (String) -> Unit = {},
) {
    val valueArray = arrayOf("1.2", "1.375", "1.55", "1.725", "1.9")
    val valueHeaderArray = stringArrayResource(id = R.array.onboardingPagerFourthValueHeaders_txt)
    val valueTipArray = stringArrayResource(id = R.array.onboardingPagerFourthValueTips_txt)
    var sliderPosition: Float by rememberSaveable { mutableStateOf(0.0f) }

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
@Preview(name = "PagerInputSlider")
@Composable
private fun PreviewPagerInputSlider() {
    TimeLeafTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            PagerInputSlider {}
        }
    }
}