package com.cyborg.currencyconverter.utils.serialize

import com.cyborg.currencyconverter.domain.models.ExchangeRates

actual class ExchangeRatesSerializer : Serializer<ExchangeRates> {

  actual override fun serialize(data: ExchangeRates) = JsonSerializer.serialize(data)

  actual override fun deSerialize(data: String) = JsonSerializer.deserialize<ExchangeRates>(data)
}
