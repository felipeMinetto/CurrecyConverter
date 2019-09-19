package com.fsm.currencyconverter.data

import com.fsm.currencyconverter.model.RateModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class RatesRepository : KoinComponent {

    private val api: CurrencyAPI by inject()

    suspend fun loadRatesForCurrencies(from: String, to: String): RateModel {
        val rate = api.getLatestValues("$from,$to", to)
        return RateModel(rate.rates[from] ?: 0f, from, to)
    }
}