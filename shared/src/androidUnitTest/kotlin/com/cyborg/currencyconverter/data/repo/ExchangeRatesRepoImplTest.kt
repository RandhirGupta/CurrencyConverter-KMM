@file:OptIn(ExperimentalCoroutinesApi::class)

package com.cyborg.currencyconverter.data.repo

import com.cyborg.currencyconverter.data.local.ExchangeRatesLocalDataSource
import com.cyborg.currencyconverter.data.remote.datasource.exchangerates.ExchangeRatesDataSource
import com.cyborg.currencyconverter.domain.repo.ExchangeRatesRepo
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.test.BeforeTest

class ExchangeRatesRepoImplTest {
  private val dataSource = mockk<ExchangeRatesDataSource>()
  private val localDataSource = mockk<ExchangeRatesLocalDataSource>()
  private lateinit var exchangeRatesRepo: ExchangeRatesRepo

  @BeforeTest
  fun setup() {
    exchangeRatesRepo = ExchangeRatesRepoImpl(dataSource, localDataSource)
  }

  @Test
  fun `Fetch Data and Store to Db`() = runBlockingTest {
    exchangeRatesRepo.fetchExchangeRates("USD")
    verify { localDataSource.insertOrUpdateExchangeRates(any()) }
  }
}
