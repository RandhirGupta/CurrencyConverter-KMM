package com.cyborg.currencyconverter.domain.poller

import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration

interface ExchangeRatesPoller {
  fun poll(delay: Duration, baseCurrency: String): Flow<Unit>
  fun close()
}
