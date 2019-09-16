package com.fsm.currencyconverter.data

import org.koin.core.KoinComponent
import org.koin.core.inject

class RatesRepository : KoinComponent {

    private val api: CurrencyAPI by inject()

    suspend fun loadRatesForCurrencies(from: String, to: String) {
        api.getLatestValues("$from,$to", to)
    }
}