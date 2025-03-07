package com.borislav.trafficlightsystem.domain.usecase

import com.borislav.trafficlightsystem.domain.model.TrafficLightState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTrafficLightStateUseCase @Inject constructor() {
    operator fun invoke(): Flow<TrafficLightState> = flow {
        while (true) {
            // Green bright 4 seconds
            emit(TrafficLightState.GREEN)
            delay(4000)
            
            // Orange bright 1 second
            emit(TrafficLightState.ORANGE)
            delay(1000)
            
            // Red bright 4 seconds
            emit(TrafficLightState.RED)
            delay(4000)
        }
    }
} 