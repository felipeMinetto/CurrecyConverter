package com.fsm.currencyconverter.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RateModel(
    @Json(name = "rates") val rates: Map<String, Float>,
    @Json(name = "base") val base: String
) {

    var destinaton: String = ""
    val rate: Float
        get() = rates[destinaton] ?: 0f
}