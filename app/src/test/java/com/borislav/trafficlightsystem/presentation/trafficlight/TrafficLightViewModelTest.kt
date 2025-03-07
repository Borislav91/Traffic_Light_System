package com.borislav.trafficlightsystem.presentation.trafficlight

import androidx.lifecycle.SavedStateHandle
import com.borislav.trafficlightsystem.domain.model.TrafficLightState
import com.borislav.trafficlightsystem.domain.usecase.GetTrafficLightStateUseCase
import com.borislav.trafficlightsystem.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class TrafficLightViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: TrafficLightViewModel
    
    @Mock
    private lateinit var getTrafficLightStateUseCase: GetTrafficLightStateUseCase
    
    private lateinit var savedStateHandle: SavedStateHandle
    
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        
        // Create a SavedStateHandle with test data
        savedStateHandle = SavedStateHandle().apply {
            set("carModel", "Tesla")
        }
    }
    
    @Test
    fun `initial state contains car model from savedStateHandle`() = runTest {
        // Set up a flow of traffic light states
        `when`(getTrafficLightStateUseCase()).thenReturn(flowOf(TrafficLightState.GREEN))
        
        // Initialize the ViewModel
        viewModel = TrafficLightViewModel(getTrafficLightStateUseCase, savedStateHandle)
        
        // Verify the initial state
        assertEquals("Tesla", viewModel.state.value.carModel)
    }
    
    @Test
    fun `when savedStateHandle has no car model, empty string is used`() = runTest {
        // Create an empty SavedStateHandle
        val emptySavedStateHandle = SavedStateHandle()
        
        // Set up a flow of traffic light states
        `when`(getTrafficLightStateUseCase()).thenReturn(flowOf(TrafficLightState.GREEN))
        
        // Initialize the ViewModel with empty SavedStateHandle
        viewModel = TrafficLightViewModel(getTrafficLightStateUseCase, emptySavedStateHandle)
        
        // Verify the initial state has an empty car model
        assertEquals("", viewModel.state.value.carModel)
    }
    
    @Test
    fun `traffic light state is updated when flow emits new state`() = runTest {
        // Set up a flow with a sequence of traffic light states
        val trafficLightFlow = flowOf(
            TrafficLightState.GREEN,
            TrafficLightState.ORANGE,
            TrafficLightState.RED
        )
        `when`(getTrafficLightStateUseCase()).thenReturn(trafficLightFlow)
        
        // Initialize the ViewModel - with the UnconfinedTestDispatcher, this will process all emissions immediately
        viewModel = TrafficLightViewModel(getTrafficLightStateUseCase, savedStateHandle)
        
        // The state should be updated to the last emitted value
        assertEquals(TrafficLightState.RED, viewModel.state.value.currentLight)
    }
    
    @Test
    fun `handleAction does nothing as it is not implemented`() = runTest {
        // Set up a flow of traffic light states
        `when`(getTrafficLightStateUseCase()).thenReturn(flowOf(TrafficLightState.GREEN))
        
        // Initialize the ViewModel
        viewModel = TrafficLightViewModel(getTrafficLightStateUseCase, savedStateHandle)
        
        // Capture the current state
        val initialState = viewModel.state.value
        
        // Call handleAction which should do nothing
        viewModel.handleAction(Unit)
        
        // Verify the state remains unchanged
        assertEquals(initialState, viewModel.state.value)
    }
} 