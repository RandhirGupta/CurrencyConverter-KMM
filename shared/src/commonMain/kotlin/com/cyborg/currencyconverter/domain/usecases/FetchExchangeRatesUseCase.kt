package com.cyborg.currencyconverter.domain.usecases

import com.cyborg.currencyconverter.domain.repo.ExchangeRatesRepo

class FetchExchangeRatesUseCase(
  private val exchangeRatesRepo: ExchangeRatesRepo,
) {

  suspend operator fun invoke(baseCurrency: String) {
    exchangeRatesRepo.fetchExchangeRates(baseCurrency)
  }
}
