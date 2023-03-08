package com.cyborg.currencyconverter.domain.usecases

import com.cyborg.currencyconverter.domain.models.ExchangeRates
import com.cyborg.currencyconverter.domain.repo.ExchangeRatesRepo
import kotlinx.coroutines.flow.Flow

class GetExchangeRatesUseCase(
  private val exchangeRatesRepo: ExchangeRatesRepo,
) {

  suspend operator fun invoke(baseCurrency: String): Flow<ExchangeRates> =
    exchangeRatesRepo.getExchangeRates(baseCurrency)
}
