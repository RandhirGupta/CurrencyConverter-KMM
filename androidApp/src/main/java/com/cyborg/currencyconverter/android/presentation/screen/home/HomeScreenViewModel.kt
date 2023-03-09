package com.cyborg.currencyconverter.android.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyborg.currencyconverter.domain.usecases.FetchExchangeRatesUseCase
import com.cyborg.currencyconverter.domain.usecases.GetExchangeRatesUseCase
import com.cyborg.currencyconverter.presentation.home.HomeScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
@Inject constructor(
  fetchExchangeRatesUseCase: FetchExchangeRatesUseCase,
  getExchangeRatesUseCase: GetExchangeRatesUseCase,
) : ViewModel() {

  private val viewModel = HomeScreenViewModel(viewModelScope.coroutineContext, fetchExchangeRatesUseCase, getExchangeRatesUseCase)
  val baseCurrency get() = viewModel.baseCurrency

  val uiState = viewModel.exchangeRatesStateFlow

  fun updateBaseCurrencyUpdate(currency: String) {
    viewModel.onBaseCurrencyUpdate(currency)
  }

  fun updateBaseCurrencyValue(value: String) {
    val newValue = if (value.isBlank()) 1.0 else value.toDouble()
    viewModel.onBaseCurrencyValueChanged(newValue)
  }
}
