package com.borislav.trafficlightsystem.presentation.trafficlight

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.borislav.trafficlightsystem.domain.usecase.GetTrafficLightStateUseCase
import com.borislav.trafficlightsystem.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TrafficLightViewModel @Inject constructor(
    private val getTrafficLightStateUseCase: GetTrafficLightStateUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<TrafficLightUiState, Unit>(TrafficLightUiState()) {

    init {
        val carModel = savedStateHandle.get<String>("carModel") ?: ""
        updateState { it.copy(carModel = carModel) }
        observeTrafficLight()
    }

    private fun observeTrafficLight() {
        getTrafficLightStateUseCase().onEach { trafficLightState ->
            updateState { it.copy(currentLight = trafficLightState) }
        }.launchIn(viewModelScope)
    }

    override fun handleAction(action: Unit) {}
} 