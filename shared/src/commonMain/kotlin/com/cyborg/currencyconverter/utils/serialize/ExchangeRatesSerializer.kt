package com.cyborg.currencyconverter.utils.serialize

import com.cyborg.currencyconverter.domain.models.ExchangeRates

expect class ExchangeRatesSerializer() : Serializer<ExchangeRates> {
  override fun serialize(data: ExchangeRates): String
  override fun deSerialize(data: String): ExchangeRates
}
