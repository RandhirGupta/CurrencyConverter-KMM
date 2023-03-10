@file:OptIn(ExperimentalCoroutinesApi::class)

package com.cyborg.currencyconverter.presenter.home

import com.cyborg.currencyconverter.data.local.ExchangeRatesLocalDataSource
import com.cyborg.currencyconverter.domain.poller.ExchangeRatesPoller
import com.cyborg.currencyconverter.domain.repo.ExchangeRatesRepo
import com.cyborg.currencyconverter.domain.usecases.GetExchangeRatesUseCase
import com.cyborg.currencyconverter.presentation.home.HomeScreenState
import com.cyborg.currencyconverter.presentation.home.HomeScreenViewModel
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeScreenViewModelTest {
  private val testDispatcher = TestCoroutineDispatcher()
  private val exchangeRatesRepo = mockk<ExchangeRatesRepo>()
  private val exchangeRatesPoller = mockk<ExchangeRatesPoller>()
  private val localDataSource = mockk<ExchangeRatesLocalDataSource>()
  private val getExchangeRatesUseCase = GetExchangeRatesUseCase(exchangeRatesRepo)

  private lateinit var viewModel: HomeScreenViewModel

  @BeforeTest
  fun setup() {
    viewModel = HomeScreenViewModel(testDispatcher, getExchangeRatesUseCase, exchangeRatesPoller)
  }

  @Test
  fun `Home screen Loading State`() = runBlockingTest {
    assertEquals(viewModel.exchangeRatesStateFlow.value, HomeScreenState.Loading)
  }

  @Test
  fun `Home screen Success State`() = runBlockingTest {
    localDataSource.insertOrUpdateExchangeRates(mockk())
    assertEquals(viewModel.exchangeRatesStateFlow.value, HomeScreenState.Success(mockk()))
  }
}
