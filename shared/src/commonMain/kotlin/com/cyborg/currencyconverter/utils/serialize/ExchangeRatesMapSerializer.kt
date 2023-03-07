package com.cyborg.currencyconverter.utils.serialize

expect class ExchangeRatesMapSerializer() : Serializer<Map<String, Double>> {
  override fun serialize(data: Map<String, Double>): String
  override fun deSerialize(data: String): Map<String, Double>
}
