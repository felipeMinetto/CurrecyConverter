package com.fsm.currencyconverter.model

data class RateModel(
        var rate: Float = 0f,
        var fromCurrency: String,
        var toCurrency: String
)