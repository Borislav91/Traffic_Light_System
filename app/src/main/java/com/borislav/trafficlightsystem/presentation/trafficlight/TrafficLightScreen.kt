package com.borislav.trafficlightsystem.presentation.trafficlight

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.borislav.trafficlightsystem.R
import com.borislav.trafficlightsystem.domain.model.TrafficLightState
import com.borislav.trafficlightsystem.ui.theme.TrafficLightGreen
import com.borislav.trafficlightsystem.ui.theme.TrafficLightOrange
import com.borislav.trafficlightsystem.ui.theme.TrafficLightRed

@Composable
fun TrafficLightScreen(
    viewModel: TrafficLightViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.traffic_light_car_model, state.carModel),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(64.dp))
            
            TrafficLightView(state.currentLight)
        }
    }
}

@Composable
fun TrafficLightView(currentLight: TrafficLightState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val redAlpha by animateFloatAsState(
            targetValue = if (currentLight == TrafficLightState.RED) 1f else 0.3f,
            animationSpec = tween(durationMillis = 500),
            label = "redAlpha"
        )
        TrafficLight(color = TrafficLightRed, alpha = redAlpha)

        val orangeAlpha by animateFloatAsState(
            targetValue = if (currentLight == TrafficLightState.ORANGE) 1f else 0.3f,
            animationSpec = tween(durationMillis = 500),
            label = "orangeAlpha"
        )
        TrafficLight(color = TrafficLightOrange, alpha = orangeAlpha)

        val greenAlpha by animateFloatAsState(
            targetValue = if (currentLight == TrafficLightState.GREEN) 1f else 0.3f,
            animationSpec = tween(durationMillis = 500),
            label = "greenAlpha"
        )
        TrafficLight(color = TrafficLightGreen, alpha = greenAlpha)
    }
}

@Composable
fun TrafficLight(color: Color, alpha: Float) {
    Box(modifier = Modifier.size(80.dp)) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .alpha(alpha)
        ) {
            drawCircle(
                color = color,
                radius = size.minDimension / 2
            )
        }
    }
} 