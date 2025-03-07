package com.borislav.trafficlightsystemxml.presentation.trafficlight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.borislav.trafficlightsystemxml.domain.usecase.GetTrafficLightStateUseCase

class TrafficLightViewModelFactory(
    private val getTrafficLightStateUseCase: GetTrafficLightStateUseCase,
    private val carModel: String
) : ViewModelProvider.Factory {
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrafficLightViewModel::class.java)) {
            return TrafficLightViewModel(getTrafficLightStateUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 