package com.cyborg.currencyconverter.data.models.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRatesResponse(
  @SerialName("disclaimer") val disclaimer: String,
  @SerialName("license") val license: String,
  @SerialName("timestamp") val timestamp: Int,
  @SerialName("base") val base: String,
  @SerialName("rates") val rates: Map<String, Double>,
)
