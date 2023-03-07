package com.cyborg.currencyconverter.utils.serialize

import com.cyborg.currencyconverter.domain.models.ExchangeRates
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

actual class ExchangeRatesSerializer : Serializer<ExchangeRates> {

  actual override fun serialize(data: ExchangeRates) = json.encodeToString(data)

  actual override fun deSerialize(data: String) = json.decodeFromString<ExchangeRates>(data)
}
