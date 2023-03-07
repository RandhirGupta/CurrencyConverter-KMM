package com.cyborg.currencyconverter.domain.models

data class ExchangeRates(
  val base: String,
  val rates: HashMap<String, Double>,
)
