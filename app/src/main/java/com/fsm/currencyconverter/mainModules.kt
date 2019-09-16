package com.fsm.currencyconverter

import com.fsm.currencyconverter.data.CurrencyAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val API_ENDPOINT = "https://api.exchangeratesapi.io/"

val module = org.koin.dsl.module {
    single { provideDefaultOkhttpClient() }
    single { provideAPI(get()) }
}

fun provideDefaultOkhttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
}

fun provideAPI(client: OkHttpClient): CurrencyAPI {
    return Retrofit.Builder()
        .baseUrl(API_ENDPOINT)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build().create(CurrencyAPI::class.java)
}
