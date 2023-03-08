package com.cyborg.currencyconverter.presentation.home

import com.cyborg.currencyconverter.domain.models.ExchangeRates

sealed class HomeScreenState {
  object Loading : HomeScreenState()
  data class Success(val exchangeRates: ExchangeRates) : HomeScreenState()
  data class Error(val error: Throwable) : HomeScreenState()
}
