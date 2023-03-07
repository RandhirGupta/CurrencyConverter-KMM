package com.cyborg.currencyconverter.utils.serialize

import com.cyborg.currencyconverter.data.models.remote.ExchangeRatesResponse

actual class ExchangeRatesResponseSerializer : Serializer<ExchangeRatesResponse> {
  actual override fun serialize(data: ExchangeRatesResponse) = JsonSerializer.serialize(data)

  actual override fun deSerialize(data: String) = JsonSerializer.deserialize<ExchangeRatesResponse>(data)
}
