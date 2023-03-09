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
) : CoroutineScope by CoroutineScope(coroutineContext) {

  var baseCurrency = "USD"
  private var exchangeRates: ExchangeRates? = null
  private var updatedExchangeRates: ExchangeRates? = null
  private val exchangeRatesMutableSharedFlow = MutableSharedFlow<HomeScreenState>(replay = 1, onBufferOverflow = DROP_LATEST)
  val exchangeRatesStateFlow: StateFlow<HomeScreenState> = exchangeRatesMutableSharedFlow.toStateFlow(this, Loading)

  init {
    fetchExchangeRates()
    launch { getExchangeRates() }
  }

  private fun fetchExchangeRates() {
    exchangeRatesMutableSharedFlow.tryEmit(Loading)
    fetchExchangeRatesUseCase(baseCurrency, coroutineContext)
  }

  private suspend fun getExchangeRates() {
    getExchangeRatesUseCase(baseCurrency)
      .catch { exchangeRatesMutableSharedFlow.tryEmit(Error(it)) }
      .collect {
        exchangeRates = it
        updatedExchangeRates = exchangeRates
        exchangeRatesMutableSharedFlow.tryEmit(Success(it))
      }
  }

  fun onBaseCurrencyUpdate(baseCurrency: String) {
    val exchangeRates = exchangeRates ?: return
    val newBaseCurrency: CurrencyRate = exchangeRates.currenciesRates.find { it.name == baseCurrency } ?: return

    this.baseCurrency = baseCurrency

    val newExchangeValue: Double = 1 / newBaseCurrency.value
    val newCurrenciesRates = getCurrenciesRates(exchangeRates, baseCurrency, newExchangeValue)
    val newExchangeRates = exchangeRates.copy(currenciesRates = newCurrenciesRates)
    updatedExchangeRates = newExchangeRates
    updateExchangeRatesStream(newExchangeRates)
  }

  fun onBaseCurrencyValueChanged(value: Double) {
    val exchangeRates = updatedExchangeRates ?: return

    val newCurrenciesRates = getCurrenciesRates(exchangeRates, baseCurrency, value)
    val newExchangeRates = exchangeRates.copy(currenciesRates = newCurrenciesRates)
    updateExchangeRatesStream(newExchangeRates)
  }

  private fun getCurrenciesRates(exchangeRates: ExchangeRates, baseCurrency: String, newExchangeValue: Double) = exchangeRates.currenciesRates.map {
    if (it.name != baseCurrency) it.copy(value = it.value * newExchangeValue)
    else it.copy(value = 1.0)
  }

  private fun updateExchangeRatesStream(exchangeRates: ExchangeRates) {
    exchangeRatesMutableSharedFlow.tryEmit(Success(exchangeRates))
  }
}
