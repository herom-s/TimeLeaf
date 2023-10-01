package com.project.timeleaf.ui.onboarding.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.project.timeleaf.R
import com.project.timeleaf.ui.theme.TimeLeafTheme

@Composable
fun PagerText(title: String) {
    Text(
        text = title,
        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Preview(name = "PagerText")
@Composable
private fun PreviewPagerText() {
    TimeLeafTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            stringArrayResource(id = R.array.onboardingPagerHeaders_txt).forEach {
                PagerText(it)
            }
        }
    }
}