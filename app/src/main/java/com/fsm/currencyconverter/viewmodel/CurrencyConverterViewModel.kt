package com.fsm.currencyconverter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fsm.currencyconverter.data.RatesRepository
import com.fsm.currencyconverter.model.RateModel
import com.fsm.currencyconverter.usecase.ConvertionUseCase
import kotlinx.coroutines.*

class CurrencyConverterViewModel : ViewModel() {

    private val repository = RatesRepository()

    private var rate: RateModel? = null

    val baseValue: MutableLiveData<Float> = MutableLiveData(1f)

    val convertedValue: MutableLiveData<Float> = MutableLiveData(0f)

    private var job: Job? = null

    fun loadRate() {
        job = GlobalScope.launch {
            val rateModel = repository.loadRatesForCurrencies("PLN", "USD")
            withContext(Dispatchers.Main) {
                rate = rateModel
            }
        }
    }

    fun updatedBaseCurrencyValue(value: String) {
        ConvertionUseCase.getConvertedValueFromString(value, rate)?.let {
            convertedValue.value = it
        }
    }

    fun updatedCurrencyValue(value: String) {
        ConvertionUseCase.getBaseValueFromString(value, rate)?.let {
            baseValue.value = it
        }
    }

    fun getFormattedBaseValue(): String {
        return ConvertionUseCase.getFormattedValue(baseValue.value)
    }

    fun getFormattedConvertedValue(): String {
        return ConvertionUseCase.getFormattedValue(convertedValue.value)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}