package com.borislav.trafficlightsystem.presentation.trafficlight

import com.borislav.trafficlightsystem.domain.model.TrafficLightState

data class TrafficLightUiState(
    val carModel: String = "",
    val currentLight: TrafficLightState = TrafficLightState.RED
) 