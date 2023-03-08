package com.cyborg.currencyconverter.domain.mapper

import com.cyborg.currencyconverter.domain.models.CurrencyRate
import com.cyborg.currencyconverter.domain.models.ExchangeRates
import com.cyborg.currencyconverter.utils.serialize.ExchangeRatesMapSerializer
import database.ExchangeRatesEntity

val exchangeRatesMapSerializer = ExchangeRatesMapSerializer()

fun ExchangeRatesEntity.toExchangeRates() = ExchangeRates(
  base = base,
  currenciesRates = exchangeRatesMapSerializer.deSerialize(rates).toCurrenciesRates()
)

private fun Map<String, Double>.toCurrenciesRates(): List<CurrencyRate> = map { CurrencyRate(it.key, it.value) }
