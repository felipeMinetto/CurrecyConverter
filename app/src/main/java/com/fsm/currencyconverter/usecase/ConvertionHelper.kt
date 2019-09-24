package com.fsm.currencyconverter.usecase

import com.fsm.currencyconverter.model.RateModel

object ConvertionHelper {

    fun getConvertedValueFromString(value: String, rate: RateModel?): Float? {
        return rate?.let {
            if (value.isEmpty()) return null

            return (value.toFloatOrNull() ?: return null) * it.rate
        }
    }

    fun getBaseValueFromString(value: String, rate: RateModel?): Float? {
        return rate?.let {
            if (value.isEmpty()) return null

            return (value.toFloatOrNull() ?: return null) / it.rate
        }
    }

    fun getFormattedValue(value: Float?): String {
        return String.format(String.format("%.2f", value ?: 0f))
    }
}