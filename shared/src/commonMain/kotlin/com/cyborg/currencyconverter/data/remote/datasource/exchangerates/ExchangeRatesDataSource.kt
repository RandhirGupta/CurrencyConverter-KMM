package com.cyborg.currencyconverter.data.remote.datasource.exchangerates

import com.cyborg.currencyconverter.data.models.remote.ExchangeRatesResponse
import com.cyborg.currencyconverter.data.remote.datasource.RemoteDataSource

class ExchangeRatesDataSource : RemoteDataSource() {

  companion object {
    private const val API_PREFIX = "api/"
    private const val LATEST_EXCHANGE_RATES = "${API_PREFIX}latest.json"
  }

  suspend fun getLatestExchangeRates(base: String): ExchangeRatesResponse =
    get(LATEST_EXCHANGE_RATES) {
      url { parameters.append("base", base) }
    }
}
