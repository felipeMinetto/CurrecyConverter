package com.fsm.currencyconverter.usecase

import com.fsm.currencyconverter.model.RateModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ConvertionHelperTest {

    val rate = RateModel(
        mapOf("PLN" to 1f, "USD" to 5f),
        "PLN"
    )

    @Before
    fun setUp() {
        rate.destinaton = "USD"
    }

    @Test
    fun convertValueWithEmptyString() {
        val value = ""

        val result = ConvertionHelper.getConvertedValueFromString(value, rate)
        assertNull(result)
    }

    @Test
    fun convertValueWithTextualString() {
        val value = "asdf"

        val result = ConvertionHelper.getConvertedValueFromString(value, rate)
        assertNull(result)
    }

    @Test
    fun convertValueWithInvalidNumber() {
        val value = "1,2"

        val result = ConvertionHelper.getConvertedValueFromString(value, rate)
        assertNull(result)
    }

    @Test
    fun convertValueWithValidNumber() {
        val value = "1.2"
        val expected = 6f

        val result = ConvertionHelper.getConvertedValueFromString(value, rate)
        assertEquals(result, expected)
    }

    @Test
    fun baseValueWithEmptyString() {
        val value = ""

        val result = ConvertionHelper.getBaseValueFromString(value, rate)
        assertNull(result)
    }

    @Test
    fun baseValueWithTextualString() {
        val value = "asdf"

        val result = ConvertionHelper.getBaseValueFromString(value, rate)
        assertNull(result)
    }

    @Test
    fun baseValueWithInvalidNumber() {
        val value = "1,2"

        val result = ConvertionHelper.getBaseValueFromString(value, rate)
        assertNull(result)
    }

    @Test
    fun baseValueWithValidNumber() {
        val value = "1.2"
        val expected = 1.2f / 5f

        val result = ConvertionHelper.getBaseValueFromString(value, rate)
        assertEquals(result, expected)
    }

    @Test
    fun getFormattedValueFromNull() {
        val value = null
        val expected = "0.00"

        val result = ConvertionHelper.getFormattedValue(value)
        assertEquals(result, expected)
    }

    @Test
    fun getFormattedValueFromValue() {
        val value = 1.35f
        val expected = "1.35"

        val result = ConvertionHelper.getFormattedValue(value)
        assertEquals(result, expected)
    }

}