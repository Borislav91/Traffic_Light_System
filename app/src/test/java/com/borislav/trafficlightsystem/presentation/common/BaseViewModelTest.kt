package com.borislav.trafficlightsystem.presentation.common

import com.borislav.trafficlightsystem.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BaseViewModelTest {
    
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    
    // Test implementation of BaseViewModel
    private class TestViewModel : BaseViewModel<TestState, TestAction>(TestState()) {
        override fun handleAction(action: TestAction) {
            when (action) {
                is TestAction.UpdateValue -> updateState { it.copy(value = action.value) }
                TestAction.ClearValue -> updateState { it.copy(value = 0) }
            }
        }
        
        // Expose updateState for testing
        fun testUpdateState(update: (TestState) -> TestState) {
            updateState(update)
        }
    }
    
    // Simple state for testing
    data class TestState(val value: Int = 0)
    
    // Simple actions for testing
    sealed class TestAction {
        data class UpdateValue(val value: Int) : TestAction()
        object ClearValue : TestAction()
    }
    
    @Test
    fun `updateState correctly updates the state`() = runTest {
        val viewModel = TestViewModel()
        
        // Initial state
        assertEquals(0, viewModel.state.value.value)
        
        // Update state
        viewModel.testUpdateState { it.copy(value = 42) }
        
        // Verify state was updated
        assertEquals(42, viewModel.state.value.value)
    }
    
    @Test
    fun `handleAction correctly processes actions`() = runTest {
        val viewModel = TestViewModel()
        
        // Initial state
        assertEquals(0, viewModel.state.value.value)
        
        // Handle update action
        viewModel.handleAction(TestAction.UpdateValue(42))
        assertEquals(42, viewModel.state.value.value)
        
        // Handle clear action
        viewModel.handleAction(TestAction.ClearValue)
        assertEquals(0, viewModel.state.value.value)
    }
} 