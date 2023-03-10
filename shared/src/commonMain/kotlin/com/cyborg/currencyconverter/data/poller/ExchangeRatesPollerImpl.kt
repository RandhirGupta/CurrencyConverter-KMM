@file:OptIn(ExperimentalCoroutinesApi::class)

package com.cyborg.currencyconverter.data.poller

import com.cyborg.currencyconverter.domain.poller.ExchangeRatesPoller
import com.cyborg.currencyconverter.domain.usecases.FetchExchangeRatesUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlin.time.Duration

class ExchangeRatesPollerImpl(
  private val fetchExchangeRatesUseCase: FetchExchangeRatesUseCase,
  private val dispatcher: CoroutineDispatcher,
) : ExchangeRatesPoller {

  override fun poll(delay: Duration, baseCurrency: String): Flow<Unit> = channelFlow<Unit> {
    while (!isClosedForSend) {
      fetchExchangeRatesUseCase.invoke(baseCurrency)
      delay(delay)
    }
  }.flowOn(dispatcher)

  override fun close() {
    dispatcher.cancel()
  }
}
