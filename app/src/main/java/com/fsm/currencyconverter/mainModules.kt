package com.fsm.currencyconverter

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val API_ENDPOINT = "https://api.exchangeratesapi.io/"

val module = org.koin.dsl.module {
    single { provideDefaultOkhttpClient() }
    single { provideRetrofit(get()) }
    single { provideAPI(get()) }
}

fun provideDefaultOkhttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()

    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
}


fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(API_ENDPOINT)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

fun provideAPI(retrofit: Retrofit): CurrencyAPI {
    return retrofit.create(CurrencyAPI::class.java)
}
