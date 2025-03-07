package com.borislav.trafficlightsystem.di

import com.borislav.trafficlightsystem.domain.usecase.GetTrafficLightStateUseCase
import com.borislav.trafficlightsystem.domain.usecase.ValidateCarModelUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideValidateCarModelUseCase(): ValidateCarModelUseCase {
        return ValidateCarModelUseCase()
    }
    
    @Provides
    @Singleton
    fun provideGetTrafficLightStateUseCase(): GetTrafficLightStateUseCase {
        return GetTrafficLightStateUseCase()
    }
} 