package com.borislav.trafficlightsystemxml.domain.usecase

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidateCarModelUseCaseTest {
    
    private val validateCarModelUseCase = ValidateCarModelUseCase()
    
    @Test
    fun `car model with less than 3 characters should be invalid`() {
        assertFalse(validateCarModelUseCase(""))
        assertFalse(validateCarModelUseCase("A"))
        assertFalse(validateCarModelUseCase("AB"))
    }
    
    @Test
    fun `car model with 3 or more characters should be valid`() {
        assertTrue(validateCarModelUseCase("ABC"))
        assertTrue(validateCarModelUseCase("Tesla"))
        assertTrue(validateCarModelUseCase("BMW X5"))
    }
    
    @Test
    fun `car model with spaces should be trimmed and validated`() {
        assertFalse(validateCarModelUseCase("  A  "))
        assertFalse(validateCarModelUseCase("  AB  "))
        assertTrue(validateCarModelUseCase("  ABC  "))
    }
} 