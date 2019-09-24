package com.fsm.currencyconverter.view

import android.os.Bundle
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fsm.currencyconverter.R
import com.fsm.currencyconverter.databinding.ActivityMainBinding
import com.fsm.currencyconverter.viewmodel.CurrencyConverterViewModel
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var baseTextWatcher: TextWatcher? = null
    private var convertedTextWatcher: TextWatcher? = null

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(CurrencyConverterViewModel::class.java)
    }

    private var task: TimerTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViews()
    }

    override fun onPause() {
        super.onPause()
        task?.cancel()
    }

    override fun onResume() {
        super.onResume()
        task = Timer().schedule(0L, 30000) {
            viewModel.loadRate()
        }
    }

    private fun setupViews() {
        baseTextWatcher = binding.baseValue.doAfterTextChanged {
            viewModel.updatedBaseCurrencyValue(it.toString())
        }

        convertedTextWatcher = binding.convertValue.doAfterTextChanged {
            viewModel.updatedCurrencyValue(it.toString())
        }

        viewModel.baseValue.observe(this, Observer {
            baseTextWatcher?.let {
                binding.baseValue.removeTextChangedListener(it)
                binding.baseValue.setText(viewModel.getFormattedBaseValue())
                binding.baseValue.addTextChangedListener(it)
            }
        })

        viewModel.convertedValue.observe(this, Observer {
            convertedTextWatcher?.let {
                binding.convertValue.removeTextChangedListener(it)
                binding.convertValue.setText(viewModel.getFormattedConvertedValue())
                binding.convertValue.addTextChangedListener(it)
            }
        })
    }
}
