package com.cyborg.currencyconverter.data.repo

import com.cyborg.currencyconverter.data.local.ExchangeRatesLocalDataSource
import com.cyborg.currencyconverter.data.models.mapper.toExchangeRatesEntity
import com.cyborg.currencyconverter.data.remote.datasource.exchangerates.ExchangeRatesDataSource
import com.cyborg.currencyconverter.domain.mapper.toExchangeRates
import com.cyborg.currencyconverter.domain.models.ExchangeRates
import com.cyborg.currencyconverter.domain.repo.ExchangeRatesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExchangeRatesRepoImpl(
  private val dataSource: ExchangeRatesDataSource,
  private val localDataSource: ExchangeRatesLocalDataSource,
) : ExchangeRatesRepo {

  override suspend fun fetchExchangeRates(base: String) {
    val exchangeRates = dataSource.getLatestExchangeRates(base).toExchangeRatesEntity()
    localDataSource.insertOrUpdateExchangeRates(exchangeRates)
  }

  override suspend fun getExchangeRates(base: String): Flow<ExchangeRates> {
    return localDataSource.getExchangeRatesByBase(base).map { it.toExchangeRates() }
  }
}
