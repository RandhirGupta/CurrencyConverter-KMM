package com.cyborg.currencyconverter.domain.models

data class ExchangeRates(
  val base: String,
  val currenciesRates: List<CurrencyRate>,
)

data class CurrencyRate(
  val name: String,
  val value: Double,
)
