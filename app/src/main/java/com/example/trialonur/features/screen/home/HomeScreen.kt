package com.example.trialonur.features.screen.home

import android.os.CountDownTimer
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.trialonur.R
import com.example.trialonur.data.model.States
import com.example.trialonur.features.component.ExposedDropdownMenuBoxCustom
import com.example.trialonur.features.component.MapMarker
import com.example.trialonur.util.Constants.firstOpening
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateSecondScreen: () -> Unit
) {
    val viewState = viewModel.uiState.collectAsState().value

    Content(
        viewModel,
        isLoading = viewState.isLoading,
        data = viewState.data,
    )

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Content(
    viewModel: HomeViewModel,
    isLoading: Boolean = false,
    data: List<States>?,
) {



    if (viewModel.country.size > 0) {
        ExposedDropdownMenuBoxCustom(viewModel)
    }

    if (isLoading && firstOpening) {


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.flight))
            LottieAnimation(
                composition,
                modifier = Modifier,
                restartOnPlay = true,
                alignment = Alignment.Center,
                iterations = LottieConstants.IterateForever)
        }

    } else if (data != null) {

        firstOpening = false

        val timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                viewModel.getData()
            }
        }.start()
        val latLng = LatLng(
            data.get(0).latitude.toString().toDouble(),
            data.get(0).longitude.toString().toDouble()
        )
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(
                latLng,
                3.5f
            )
        }
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 50.dp, 0.dp, 0.dp),
            cameraPositionState = cameraPositionState,
            onMapClick = {
                // get region and update data
            }
        ) {
            data.forEach { state ->

                val coordinates =
                    LatLng(state.latitude.toString().toDouble(), state.longitude.toString().toDouble())
                MapMarker(
                    position = coordinates,
                    context = LocalContext.current,
                    iconResourceId = R.drawable.ic_airplane,
                    title = state.origin_country.toString(),
                    rotation = state.true_track.toString().toFloat() ?: 0F
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun BodyPreview() {
    HomeScreen(viewModel = hiltViewModel(), onNavigateSecondScreen = {})
}