package com.borislav.trafficlightsystem.domain.usecase

import javax.inject.Inject

class ValidateCarModelUseCase @Inject constructor() {
    operator fun invoke(carModel: String): Boolean {
        return carModel.trim().length >= 3
    }
} 