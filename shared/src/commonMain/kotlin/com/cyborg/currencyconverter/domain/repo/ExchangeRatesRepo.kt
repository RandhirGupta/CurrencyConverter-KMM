package com.cyborg.currencyconverter.domain.repo

import com.cyborg.currencyconverter.domain.models.ExchangeRates
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

interface ExchangeRatesRepo {
  fun startExchangeRatesPolling(base: String, coroutineContext: CoroutineContext)
  suspend fun getExchangeRates(base: String): Flow<ExchangeRates>
}
