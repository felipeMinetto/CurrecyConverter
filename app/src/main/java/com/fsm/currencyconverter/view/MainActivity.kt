package com.fsm.currencyconverter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fsm.currencyconverter.R
import com.fsm.currencyconverter.data.RatesRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.context.GlobalContext
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = RatesRepository()
        GlobalScope.launch { repo.loadRatesForCurrencies("PLN", "USD") }
    }
}
