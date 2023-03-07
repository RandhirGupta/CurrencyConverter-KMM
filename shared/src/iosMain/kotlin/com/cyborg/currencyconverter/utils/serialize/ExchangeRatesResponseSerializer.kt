package com.cyborg.currencyconverter.utils.serialize

import com.cyborg.currencyconverter.data.models.remote.ExchangeRatesResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

actual class ExchangeRatesResponseSerializer : Serializer<ExchangeRatesResponse> {
  actual override fun serialize(data: ExchangeRatesResponse) = json.encodeToString(data)

  actual override fun deSerialize(data: String) = json.decodeFromString<ExchangeRatesResponse>(data)
}
