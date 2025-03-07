package com.borislav.trafficlightsystemxml.di

import com.borislav.trafficlightsystemxml.domain.usecase.GetTrafficLightStateUseCase
import com.borislav.trafficlightsystemxml.domain.usecase.ValidateCarModelUseCase

// Simple Service Locator pattern for manual DI
object ServiceLocator {
    
    // Lazy initialization for singletons
    private val validateCarModelUseCase by lazy { ValidateCarModelUseCase() }
    private val getTrafficLightStateUseCase by lazy { GetTrafficLightStateUseCase() }
    
    // Public getters for use cases
    fun provideValidateCarModelUseCase(): ValidateCarModelUseCase = validateCarModelUseCase
    
    fun provideGetTrafficLightStateUseCase(): GetTrafficLightStateUseCase = getTrafficLightStateUseCase
} 