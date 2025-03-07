package com.borislav.trafficlightsystem.presentation.carinput

sealed class CarInputAction {
    data class OnCarModelChange(val carModel: String) : CarInputAction()
    data object OnStartDrivingClick : CarInputAction()
} 