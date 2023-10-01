package com.project.timeleaf.ui.onboarding.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import com.project.timeleaf.R

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
@Preview(name = "PagerInputDropDown")
@Composable
private fun PreviewPagerInputDropDown() {
    PagerInputDropDown {}
}