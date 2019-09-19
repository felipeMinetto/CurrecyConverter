package com.fsm.currencyconverter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fsm.currencyconverter.data.RatesRepository
import com.fsm.currencyconverter.model.RateModel
import kotlinx.coroutines.*
import java.util.*
import kotlin.concurrent.fixedRateTimer

class CurrencyConverterViewModel : ViewModel() {

    private val repository = RatesRepository()

    private var rate: RateModel? = null

    val baseValue: MutableLiveData<Float> = MutableLiveData(1f)

    val convertedValue: MutableLiveData<Float> = MutableLiveData(0f)

    private var job: Job? = null
    private var timer: Timer? = null

    fun loadRate() {
        timer?.cancel()
        timer = fixedRateTimer("loader", false, 0, 30000) {
            job = GlobalScope.launch {
                val rateModel = repository.loadRatesForCurrencies("PLN", "USD")
                withContext(Dispatchers.Main) {
                    rate = rateModel
                }
            }
        }
    }

    fun updatedBaseCurrencyValue(value: String) {
        if (value.isEmpty().not()) {
            rate?.let {
                convertedValue.value = (value.toFloatOrNull() ?: 0f) / it.rate
            }
        }
    }

    fun updatedCurrencyValue(value: String) {
        if (value.isEmpty().not()) {
            rate?.let {
                baseValue.value = (value.toFloatOrNull() ?: 0f) * it.rate
            }
        }
    }

    fun getFormattedBaseValue(): String {
        return String.format(String.format("%.2f", baseValue.value ?: 0))
    }

    fun getFormattedConvertedValue(): String {
        return String.format(String.format("%.2f", convertedValue.value ?: 0))
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
        timer?.cancel()
    }

    fun cancelRateloader() {
        job?.cancel()
        timer?.cancel()
    }
}