package com.project.timeleaf.ui.coach

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.designsystem.theme.TlTheme

@Composable
fun CoachScreen(coachViewModel: CoachViewModel = hiltViewModel()){
    val uiState = coachViewModel.uiState.collectAsState()

    Column {
        Text("Name: ${uiState.value.name}")
        Text("Gender: ${uiState.value.gender}")
        Text("Age: ${uiState.value.age}")
        Text("Level of physical activity: ${uiState.value.lvlPhysicalActivity}")
        Text("Weight: ${uiState.value.weight}")
        Text("Height: ${uiState.value.height}")
        Text("BMI: ${uiState.value.bmi}")
        Text("BMR: ${uiState.value.bmr}")
        Text("TEV: ${uiState.value.tev}")
        Coach()
    }
}

@Composable
fun Coach(
    modifier: Modifier = Modifier, names: List<String> = listOf("World", "Compose")
) {
    Column(modifier = modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            CoachStuff(name = name)
        }
    }
}

@Composable
fun CoachStuff(name: String) {
    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello,")
                Text(text = name)
            }
            ElevatedButton(onClick = { expanded.value = !expanded.value }) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimeLeafAppPreview() {
    TlTheme {
        Coach(Modifier.fillMaxSize())
    }
}