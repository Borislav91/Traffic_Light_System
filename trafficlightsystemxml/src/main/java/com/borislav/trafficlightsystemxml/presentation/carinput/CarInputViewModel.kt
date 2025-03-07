package com.borislav.trafficlightsystemxml.presentation.carinput

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.borislav.trafficlightsystemxml.R
import com.borislav.trafficlightsystemxml.domain.usecase.ValidateCarModelUseCase

class CarInputViewModel(
    application: Application,
    private val validateCarModelUseCase: ValidateCarModelUseCase,
) : AndroidViewModel(application) {

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
            _errorMessage.value = getApplication<Application>().getString(
                R.string.car_model_must_be_at_least_3_characters_long
            )
        }
        
        return isValid
    }
} 