package com.fsm.currencyconverter.usecase

import com.fsm.currencyconverter.model.RateModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ConvertionUseCaseTest {

    val rate = RateModel(
        mapOf("PLN" to 1f, "USD" to 5f),
        "PLN"
    )

    @Before
    fun setUp(){
        rate.destinaton = "USD"
    }

    @Test
    fun convertValueWithEmptyString() {
        val value = ""

        val result = ConvertionUseCase.getConvertedValueFromString(value, rate)
        assertNull(result)
    }

    @Test
    fun convertValueWithTextualString() {
        val value = "asdf"

        val result = ConvertionUseCase.getConvertedValueFromString(value, rate)
        assertNull(result)
    }

    @Test
    fun convertValueWithInvalidNumber() {
        val value = "1,2"

        val result = ConvertionUseCase.getConvertedValueFromString(value, rate)
        assertNull(result)
    }

    @Test
    fun convertValueWithValidNumber() {
        val value = "1.2"
        val expected = 6f

        val result = ConvertionUseCase.getConvertedValueFromString(value, rate)
        assertEquals(result, expected)
    }

}