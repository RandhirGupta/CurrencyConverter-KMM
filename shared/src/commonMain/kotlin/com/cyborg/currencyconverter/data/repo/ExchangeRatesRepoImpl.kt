package com.cyborg.currencyconverter.data.repo

import com.cyborg.currencyconverter.data.local.ExchangeRatesLocalDataSource
import com.cyborg.currencyconverter.data.models.mapper.toExchangeRatesEntity
import com.cyborg.currencyconverter.data.remote.datasource.exchangerates.ExchangeRatesDataSource
import com.cyborg.currencyconverter.domain.mapper.toExchangeRates
import com.cyborg.currencyconverter.domain.models.ExchangeRates
import com.cyborg.currencyconverter.domain.repo.ExchangeRatesRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.time.DurationUnit.MINUTES
import kotlin.time.toDuration

class ExchangeRatesRepoImpl(
  private val dataSource: ExchangeRatesDataSource,
  private val localDataSource: ExchangeRatesLocalDataSource,
) : ExchangeRatesRepo {

  override fun startExchangeRatesPolling(base: String, coroutineContext: CoroutineContext) {
    CoroutineScope(coroutineContext).launch {
      while (isActive) {
        fetchFetchExchangeRate(base)
        delay(30.toDuration(MINUTES))
      }
    }
  }

  private suspend fun fetchFetchExchangeRate(base: String) {
    val exchangeRates = dataSource.getLatestExchangeRates(base).toExchangeRatesEntity()
    localDataSource.insertOrUpdateExchangeRates(exchangeRates)
  }

  override suspend fun getExchangeRates(base: String): Flow<ExchangeRates> {
    return localDataSource.getExchangeRatesByBase(base).map { it.toExchangeRates() }
  }
}
