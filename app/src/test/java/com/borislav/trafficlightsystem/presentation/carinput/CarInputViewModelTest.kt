package com.borislav.trafficlightsystem.presentation.carinput

import com.borislav.trafficlightsystem.domain.usecase.ValidateCarModelUseCase
import com.borislav.trafficlightsystem.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CarInputViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: CarInputViewModel
    
    @Mock
    private lateinit var validateCarModelUseCase: ValidateCarModelUseCase
    
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        
        // Set up viewModel mock use case
        viewModel = CarInputViewModel(validateCarModelUseCase)
    }
    
    @Test
    fun `when OnCarModelChange action is handled, state is updated and error is cleared`() {
        // Initial state should have empty car model, no errors
        assertEquals("", viewModel.state.value.carModel)
        assertFalse(viewModel.state.value.isError)
        assertEquals("", viewModel.state.value.errorMessage)
        
        // Handle action car model change
        viewModel.handleAction(CarInputAction.OnCarModelChange("Tesla"))
        
        // Verify state was updated properly
        assertEquals("Tesla", viewModel.state.value.carModel)
        assertFalse(viewModel.state.value.isError)
        assertEquals("", viewModel.state.value.errorMessage)
    }
    
    @Test
    fun `when OnStartDrivingClick action is handled with valid car model, error state remains false`() {
        // Set up a valid response from the use case
        `when`(validateCarModelUseCase("Tesla")).thenReturn(true)
        
        // Set the initial state with a car model
        viewModel.handleAction(CarInputAction.OnCarModelChange("Tesla"))
        
        // Handle the click action
        viewModel.handleAction(CarInputAction.OnStartDrivingClick)
        
        // Verify state is still valid with no errors
        assertFalse(viewModel.state.value.isError)
        assertEquals("", viewModel.state.value.errorMessage)
    }
    
    @Test
    fun `when OnStartDrivingClick action is handled with invalid car model, error state is set`() {
        // Set up an invalid response from the use case
        `when`(validateCarModelUseCase("Bo")).thenReturn(false)
        
        // Set the initial state with an invalid car model
        viewModel.handleAction(CarInputAction.OnCarModelChange("Ha"))
        
        // Handle the click action
        viewModel.handleAction(CarInputAction.OnStartDrivingClick)
        
        // Verify error state is set correctly
        assertTrue(viewModel.state.value.isError)
        assertEquals("Car model must be at least 3 characters long", viewModel.state.value.errorMessage)
    }
    
    @Test
    fun `isValidInput returns result from validateCarModelUseCase`() {
        // Set up responses from the use case
        `when`(validateCarModelUseCase("")).thenReturn(false)
        `when`(validateCarModelUseCase("Te")).thenReturn(false)
        `when`(validateCarModelUseCase("Tesla")).thenReturn(true)
        
        // Test with empty string
        viewModel.handleAction(CarInputAction.OnCarModelChange(""))
        assertFalse(viewModel.isValidInput())
        
        // Test with short car model
        viewModel.handleAction(CarInputAction.OnCarModelChange("Se"))
        assertFalse(viewModel.isValidInput())
        
        // Test with valid car model
        viewModel.handleAction(CarInputAction.OnCarModelChange("Tesla"))
        assertTrue(viewModel.isValidInput())
    }
    
    @Test
    fun `when car model is changed, previous error state is cleared`() {
        // Set an error state
        `when`(validateCarModelUseCase("Au")).thenReturn(false)
        viewModel.handleAction(CarInputAction.OnCarModelChange("Vw"))
        viewModel.handleAction(CarInputAction.OnStartDrivingClick)
        
        // Verify error state is set
        assertTrue(viewModel.state.value.isError)
        assertNotEquals("", viewModel.state.value.errorMessage)
        
        //Change the car model
        viewModel.handleAction(CarInputAction.OnCarModelChange("Tesla"))
        
        // Verify error state is cleared
        assertFalse(viewModel.state.value.isError)
        assertEquals("", viewModel.state.value.errorMessage)
    }
} 