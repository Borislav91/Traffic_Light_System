package com.borislav.trafficlightsystemxml.presentation.trafficlight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borislav.trafficlightsystemxml.domain.model.TrafficLightState
import com.borislav.trafficlightsystemxml.domain.usecase.GetTrafficLightStateUseCase
import com.borislav.trafficlightsystemxml.domain.usecase.TrafficLightStateCallback

class TrafficLightViewModel(
    private val getTrafficLightStateUseCase: GetTrafficLightStateUseCase
) : ViewModel(), TrafficLightStateCallback {

    private val _currentState = MutableLiveData<TrafficLightState>()
    val currentState: LiveData<TrafficLightState> = _currentState

    init {
        startTrafficLight()
    }

    private fun startTrafficLight() {
        getTrafficLightStateUseCase.startEmittingStates(this)
    }

    override fun onTrafficLightStateChanged(state: TrafficLightState) {
        _currentState.value = state
    }

    override fun onCleared() {
        getTrafficLightStateUseCase.stopEmittingStates()
        super.onCleared()
    }
} 