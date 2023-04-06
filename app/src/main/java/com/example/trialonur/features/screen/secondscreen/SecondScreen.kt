package com.example.trialonur.features.screen.secondscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trialonur.features.ui.theme.Typography

@Composable
fun SecondScreen(viewModel: SecondScreenViewModel, onNavigateHomeScreen: () -> Unit) {

    val viewState = viewModel.uiState.collectAsState().value
    val result = viewState.data
    val position = viewState.position



    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(bottom = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Text(
                text = "${viewModel.name}",
                Modifier.padding(top = 50.dp),
                style = Typography.h6,
                fontSize = 25.sp
            )
            Text(
                text = result?.firstFlight.toString(),
                Modifier.padding(top = 10.dp, bottom = 40.dp),
                fontSize = 13.sp,
                color = Color.Gray
            )
            Text(
                text = "Height/Mass : ${result?.height}/${result?.mass}",
                Modifier.padding(bottom = 20.dp),
                fontSize = 18.sp,
            )
            Text(
                text = "Cost : ${result?.costPerLaunch}",
                Modifier.padding(bottom = 20.dp),
                fontSize = 18.sp,
            )
            Text(
                text = "LastPosition : ${position?.posX},${position?.posX}",
                Modifier.padding(bottom = 20.dp),
                fontSize = 18.sp,
            )
        }

    }

}


@Preview(showBackground = true)
@Composable
private fun BodyPreview() {
    SecondScreen(hiltViewModel(), onNavigateHomeScreen = {})
}