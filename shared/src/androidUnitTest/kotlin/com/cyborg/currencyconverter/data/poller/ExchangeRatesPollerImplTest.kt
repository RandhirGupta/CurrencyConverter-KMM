@file:OptIn(ExperimentalCoroutinesApi::class)

package com.cyborg.currencyconverter.data.poller

import com.cyborg.currencyconverter.domain.repo.ExchangeRatesRepo
import com.cyborg.currencyconverter.domain.usecases.FetchExchangeRatesUseCase
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlin.test.Test
import kotlin.time.DurationUnit.SECONDS
import kotlin.time.toDuration

class ExchangeRatesPollerImplTest {
  private val testDispatcher = TestCoroutineDispatcher()
  private val exchangeRatesRepo = mockk<ExchangeRatesRepo>()
  private val fetchExchangeRatesUseCase = FetchExchangeRatesUseCase(exchangeRatesRepo)
  private val exchangeRatesPoller = ExchangeRatesPollerImpl(fetchExchangeRatesUseCase, testDispatcher)

  @Test
  fun `should poll every second`() = runBlockingTest {
    exchangeRatesPoller.poll(1.toDuration(SECONDS), "USD")

    testDispatcher.scheduler.apply { advanceTimeBy(2.toDuration(SECONDS).inWholeMilliseconds); runCurrent() }
    exchangeRatesPoller.close()
  }
}
