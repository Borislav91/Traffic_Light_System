package com.borislav.trafficlightsystemxml.presentation.carinput

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borislav.trafficlightsystemxml.domain.usecase.ValidateCarModelUseCase

class CarInputViewModel(
    private val validateCarModelUseCase: ValidateCarModelUseCase
) : ViewModel() {

    private val _carModel = MutableLiveData("")
    val carModel: LiveData<String> = _carModel

    private val _isError = MutableLiveData(false)
    val isError: LiveData<Boolean> = _isError

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> = _errorMessage

    fun updateCarModel(model: String) {
        _carModel.value = model
        // Clear error when user types
        _isError.value = false
        _errorMessage.value = ""
    }

    fun validateCarModel(): Boolean {
        val isValid = validateCarModelUseCase(_carModel.value ?: "")
        
        if (!isValid) {
            _isError.value = true
            _errorMessage.value = "Car model must be at least 3 characters long"
        }
        
        return isValid
    }
} 