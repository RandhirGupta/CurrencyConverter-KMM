package com.cyborg.currencyconverter.data.models.mapper

import com.cyborg.currencyconverter.data.models.remote.ExchangeRatesResponse
import com.cyborg.currencyconverter.utils.serialize.ExchangeRatesMapSerializer
import com.cyborg.currencyconverter.utils.serialize.ExchangeRatesResponseSerializer
import database.ExchangeRatesEntity

val exchangeRatesResponseSerializer = ExchangeRatesResponseSerializer()
val exchangeRatesMapSerializer = ExchangeRatesMapSerializer()

fun ExchangeRatesResponse.toExchangeRatesEntity() = ExchangeRatesEntity(
  base = base,
  timestamp = timestamp.toString(),
  rates = exchangeRatesMapSerializer.serialize(rates)
)
