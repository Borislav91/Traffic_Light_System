package com.borislav.trafficlightsystemxml.domain.usecase

class ValidateCarModelUseCase {
    operator fun invoke(carModel: String): Boolean {
        return carModel.trim().length >= 3
    }
} 