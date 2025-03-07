package com.borislav.trafficlightsystemxml.presentation.carinput

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.borislav.trafficlightsystemxml.domain.usecase.ValidateCarModelUseCase

class CarInputViewModelFactory(
    private val validateCarModelUseCase: ValidateCarModelUseCase
) : ViewModelProvider.Factory {
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarInputViewModel::class.java)) {
            return CarInputViewModel(validateCarModelUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 