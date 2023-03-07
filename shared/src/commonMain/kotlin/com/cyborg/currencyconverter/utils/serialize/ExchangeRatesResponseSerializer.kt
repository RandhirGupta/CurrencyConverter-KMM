package com.cyborg.currencyconverter.utils.serialize

import com.cyborg.currencyconverter.data.models.remote.ExchangeRatesResponse

expect class ExchangeRatesResponseSerializer() : Serializer<ExchangeRatesResponse> {
  override fun serialize(data: ExchangeRatesResponse): String
  override fun deSerialize(data: String): ExchangeRatesResponse
}
