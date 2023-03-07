package com.cyborg.currencyconverter.utils.serialize

actual class ExchangeRatesMapSerializer : Serializer<Map<String, Double>> {
  actual override fun serialize(data: Map<String, Double>): String = JsonSerializer.serialize(data)
  actual override fun deSerialize(data: String): Map<String, Double> = JsonSerializer.deserialize(data)
}
