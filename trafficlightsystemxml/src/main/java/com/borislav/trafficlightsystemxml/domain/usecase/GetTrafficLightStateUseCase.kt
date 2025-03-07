package com.borislav.trafficlightsystemxml.domain.usecase

import android.os.Handler
import android.os.Looper
import com.borislav.trafficlightsystemxml.domain.model.TrafficLightState

interface TrafficLightStateCallback {
    fun onTrafficLightStateChanged(state: TrafficLightState)
}

class GetTrafficLightStateUseCase {
    private val handler = Handler(Looper.getMainLooper())
    private var callback: TrafficLightStateCallback? = null
    private var isRunning = false

    fun startEmittingStates(callback: TrafficLightStateCallback) {
        this.callback = callback
        isRunning = true
        emitState(TrafficLightState.GREEN)
    }

    fun stopEmittingStates() {
        isRunning = false
        handler.removeCallbacksAndMessages(null)
    }

    private fun emitState(state: TrafficLightState) {
        if (!isRunning) return

        callback?.onTrafficLightStateChanged(state)

        when (state) {
            TrafficLightState.GREEN -> {
                handler.postDelayed({
                    emitState(TrafficLightState.ORANGE)
                }, 4000) // 4 seconds
            }
            TrafficLightState.ORANGE -> {
                handler.postDelayed({
                    emitState(TrafficLightState.RED)
                }, 1000) // 1 second
            }
            TrafficLightState.RED -> {
                handler.postDelayed({
                    emitState(TrafficLightState.GREEN)
                }, 4000) // 4 seconds
            }
        }
    }
} 