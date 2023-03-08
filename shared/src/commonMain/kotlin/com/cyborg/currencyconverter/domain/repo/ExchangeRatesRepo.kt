package com.cyborg.currencyconverter.domain.repo

import com.cyborg.currencyconverter.domain.models.ExchangeRates
import kotlinx.coroutines.flow.Flow

interface ExchangeRatesRepo {
  suspend fun fetchExchangeRates(base: String)
  suspend fun getExchangeRates(base: String): Flow<ExchangeRates>
}
