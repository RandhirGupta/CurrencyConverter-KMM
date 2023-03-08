package com.cyborg.currencyconverter.presentation.home

import com.cyborg.currencyconverter.domain.models.CurrencyRate
import com.cyborg.currencyconverter.domain.models.ExchangeRates
import com.cyborg.currencyconverter.domain.usecases.FetchExchangeRatesUseCase
import com.cyborg.currencyconverter.domain.usecases.GetExchangeRatesUseCase
import com.cyborg.currencyconverter.presentation.home.HomeScreenState.*
import com.cyborg.currencyconverter.utils.flows.toStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow.DROP_LATEST
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeScreenViewModel(
  coroutineContext: CoroutineContext,
  private val fetchExchangeRatesUseCase: FetchExchangeRatesUseCase,
  private val getExchangeRatesUseCase: GetExchangeRatesUseCase,
  private val baseCurrency: String,
) : CoroutineScope by CoroutineScope(coroutineContext) {

  private var exchangeRates: ExchangeRates? = null
  private val exchangeRatesMutableSharedFlow = MutableSharedFlow<HomeScreenState>(replay = 1, onBufferOverflow = DROP_LATEST)
  val exchangeRatesStateFlow: StateFlow<HomeScreenState> = exchangeRatesMutableSharedFlow.toStateFlow(this, Loading)

  init {
    launch { fetchExchangeRates() }
    launch { getExchangeRates() }
  }

  private suspend fun fetchExchangeRates() {
    exchangeRatesMutableSharedFlow.tryEmit(Loading)
    fetchExchangeRatesUseCase(baseCurrency)
  }

  private suspend fun getExchangeRates() {
    getExchangeRatesUseCase(baseCurrency)
      .catch { exchangeRatesMutableSharedFlow.tryEmit(Error(it)) }
      .collect {
        exchangeRates = it
        exchangeRatesMutableSharedFlow.tryEmit(Success(it))
      }
  }

  fun onBaseCurrencyUpdate(baseCurrency: String) {
    val exchangeRates = exchangeRates ?: return
    val newBaseCurrency: CurrencyRate = exchangeRates.currenciesRates.find { it.name == baseCurrency } ?: return

    val newExchangeValue: Double = 1 / newBaseCurrency.value
    updateExchangeRates(exchangeRates, newExchangeValue)
  }

  fun onBaseCurrencyValueChanged(value: Double) {
    val exchangeRates = exchangeRates ?: return

    updateExchangeRates(exchangeRates, value)
  }

  private fun updateExchangeRates(exchangeRates: ExchangeRates, newExchangeValue: Double) {
    val newCurrenciesRates = exchangeRates.currenciesRates.map { CurrencyRate(it.name, it.value * newExchangeValue) }
    val newExchangeRates = exchangeRates.copy(currenciesRates = newCurrenciesRates)
    exchangeRatesMutableSharedFlow.tryEmit(Success(newExchangeRates))
  }
}
