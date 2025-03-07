package com.borislav.trafficlightsystem.presentation.carinput

import com.borislav.trafficlightsystem.domain.usecase.ValidateCarModelUseCase
import com.borislav.trafficlightsystem.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CarInputViewModel @Inject constructor(
    private val validateCarModelUseCase: ValidateCarModelUseCase
) : BaseViewModel<CarInputState, CarInputAction>(CarInputState()) {

    override fun handleAction(action: CarInputAction) {
        when (action) {
            is CarInputAction.OnCarModelChange -> {
                updateState { it.copy(carModel = action.carModel, isError = false, errorMessage = "") }
            }
            CarInputAction.OnStartDrivingClick -> validateCarModel()
        }
    }

    private fun validateCarModel() {
        val isValid = validateCarModelUseCase(state.value.carModel)
        if (!isValid) {
            updateState { 
                it.copy(
                    isError = true, 
                    errorMessage = "Car model must be at least 3 characters long"
                ) 
            }
        }
    }

    fun isValidInput(): Boolean {
        return validateCarModelUseCase(state.value.carModel)
    }
} 