package com.cyborg.currencyconverter.utils.serialize

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object JsonSerializer {

  val json = Json { encodeDefaults = true }

  inline fun <reified T> serialize(data: T) = json.encodeToString(data)

  inline fun <reified T> deserialize(data: String) = json.decodeFromString<T>(data)
}
