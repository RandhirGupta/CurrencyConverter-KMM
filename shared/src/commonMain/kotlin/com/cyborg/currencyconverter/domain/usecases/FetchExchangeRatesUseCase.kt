package com.cyborg.currencyconverter.domain.usecases

import com.cyborg.currencyconverter.domain.repo.ExchangeRatesRepo
import kotlin.coroutines.CoroutineContext

class FetchExchangeRatesUseCase(
  private val exchangeRatesRepo: ExchangeRatesRepo,
) {

  operator fun invoke(baseCurrency: String, coroutineContext: CoroutineContext) {
    exchangeRatesRepo.startExchangeRatesPolling(baseCurrency, coroutineContext)
  }
}
