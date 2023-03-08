package com.cyborg.currencyconverter.data.models.mapper

import com.cyborg.currencyconverter.data.models.remote.ExchangeRatesResponse
import com.cyborg.currencyconverter.utils.serialize.ExchangeRatesMapSerializer
import database.ExchangeRatesEntity

val exchangeRatesMapSerializer = ExchangeRatesMapSerializer()

fun ExchangeRatesResponse.toExchangeRatesEntity() = ExchangeRatesEntity(
  base = base,
  timestamp = timestamp.toString(),
  rates = exchangeRatesMapSerializer.serialize(rates)
)
