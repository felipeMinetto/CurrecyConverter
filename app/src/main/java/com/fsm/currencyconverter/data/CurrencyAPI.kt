package com.fsm.currencyconverter.data

import com.fsm.currencyconverter.model.RateModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyAPI {

    @GET("latest")
    suspend fun getLatestValues(
        @Query("symbols") symbols: String,
        @Query("base") base: String
    ): RateModel
}