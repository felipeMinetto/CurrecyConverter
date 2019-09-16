package com.fsm.currencyconverter.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RatesResponse(
    @Json(name = "rates") val rates: Map<String, Float>,
    @Json(name = "base") val base: String
)