package com.borislav.trafficlightsystem.presentation.carinput

data class CarInputState(
    val carModel: String = "",
    val isError: Boolean = false,
    val errorMessage: String = ""
) 