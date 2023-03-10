@file:OptIn(ExperimentalCoroutinesApi::class)

package com.cyborg.currencyconverter.data.remote.datasource.exchangerates

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class ExchangeRatesDataSourceTest {

  private lateinit var dataSource: ExchangeRatesDataSource

  @BeforeTest
  fun setup() {
    dataSource = ExchangeRatesDataSource()
  }

  @Test
  fun `Fetch the exchange rates from remote`() = runTest {
    val exchangeRatesResponse = dataSource.getLatestExchangeRates("USD")
    assertTrue(exchangeRatesResponse.rates.isNotEmpty(), "Check if the exchange rate data is present")
    assertTrue(exchangeRatesResponse.base == "USD", "Check if the base currency is matching with the API response")
  }
}
