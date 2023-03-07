package com.cyborg.currencyconverter.utils.serialize

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

actual class ExchangeRatesMapSerializer : Serializer<Map<String, Double>> {
  actual override fun serialize(data: Map<String, Double>): String = json.encodeToString(data)
  actual override fun deSerialize(data: String): Map<String, Double> = json.decodeFromString(data)
}
