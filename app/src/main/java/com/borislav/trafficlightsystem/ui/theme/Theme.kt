package com.borislav.trafficlightsystem.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable



@Composable
fun TrafficLightSystemTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        typography = Typography,
        content = content
    )
}