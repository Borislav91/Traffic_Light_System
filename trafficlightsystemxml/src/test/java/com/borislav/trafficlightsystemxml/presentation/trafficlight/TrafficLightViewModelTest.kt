package com.borislav.trafficlightsystemxml.presentation.trafficlight

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.borislav.trafficlightsystemxml.domain.model.TrafficLightState
import com.borislav.trafficlightsystemxml.domain.usecase.GetTrafficLightStateUseCase
import com.borislav.trafficlightsystemxml.domain.usecase.TrafficLightStateCallback
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class TrafficLightViewModelTest {
    
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    
    @Mock
    private lateinit var getTrafficLightStateUseCase: GetTrafficLightStateUseCase
    
    @Captor
    private lateinit var callbackCaptor: ArgumentCaptor<TrafficLightStateCallback>
    
    private lateinit var viewModel: TrafficLightViewModel
    
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = TrafficLightViewModel(getTrafficLightStateUseCase, "Test Car")
    }
    
    @Test
    fun `viewModel should register as callback to use case`() {
        // Verify that startEmittingStates was called with the viewModel as callback
        verify(getTrafficLightStateUseCase).startEmittingStates(callbackCaptor.capture())
        assertEquals(viewModel, callbackCaptor.value)
    }
    
    @Test
    fun `viewModel should update state when callback is triggered`() {
        // Manually trigger the callback
        viewModel.onTrafficLightStateChanged(TrafficLightState.RED)
        
        // Verify that the LiveData was updated
        assertEquals(TrafficLightState.RED, viewModel.currentState.value)
    }
    
    @Test
    fun `viewModel should stop emitting states when cleared`() {
        viewModel.onCleared()
        verify(getTrafficLightStateUseCase).stopEmittingStates()
    }
} 