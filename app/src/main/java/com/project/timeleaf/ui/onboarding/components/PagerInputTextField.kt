package com.project.timeleaf.ui.onboarding.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import com.project.timeleaf.R

@Composable
fun PagerInputTextField(
    label: String,
    onValueChange: (String) -> Unit,
) {
    val textValue = rememberSaveable {
        mutableStateOf("")
    }

    TextField(value = textValue.value, onValueChange = {
        textValue.value = it
        onValueChange(it)
    }, label = { Text(text = label) })
}

@Preview(name = "PagerInputTextField")
@Composable
private fun PreviewPagerInputTextField() {

    Column(modifier = Modifier.fillMaxSize()) {
        stringArrayResource(id = R.array.onboardingPagerLabels_txt).forEach {
            PagerInputTextField(it) {}
        }
    }
}