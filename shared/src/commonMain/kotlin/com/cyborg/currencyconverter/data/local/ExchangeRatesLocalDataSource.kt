package com.cyborg.currencyconverter.data.local

import com.cyborg.currencyconverter.database.ExchangeRatesDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import database.ExchangeRatesEntity
import kotlinx.coroutines.flow.Flow

class ExchangeRatesLocalDataSource(database: ExchangeRatesDatabase) {

  private val queries = database.exchangeRatesEntityQueries
  fun insertOrUpdateExchangeRates(exchangeRatesEntity: ExchangeRatesEntity) {
    queries.insertOrUpdateExchangeRates(exchangeRatesEntity.base, exchangeRatesEntity.timestamp, exchangeRatesEntity.rates)
  }

  fun getExchangeRatesByBase(base: String): Flow<ExchangeRatesEntity> =
    queries.getExchangeRatesByBase(base).asFlow().mapToOne()
}
