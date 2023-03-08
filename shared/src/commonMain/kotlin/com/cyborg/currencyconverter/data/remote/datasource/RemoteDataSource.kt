package com.cyborg.currencyconverter.data.remote.datasource

import com.cyborg.currencyconverter.utils.Constants.BASE_URL
import com.cyborg.currencyconverter.utils.Secrets.APP_ID
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*

abstract class RemoteDataSource {

  val baseUrl = BASE_URL
  val appId = APP_ID

  val client = HttpClient {
    install(Logging) {
      logger = Logger.DEFAULT
      level = LogLevel.ALL
    }
    install(ContentNegotiation) { json() }
  }

  suspend inline fun <reified T> get(
    url: String,
    crossinline builder: HttpRequestBuilder.() -> Unit,
  ): T = api {
    client.get("$baseUrl/$url") {
      builder()
      url { parameters.append("app_id", appId) }
    }.body()
  }


  suspend fun <T> api(call: suspend () -> T): T = try {
    call()
  } catch (e: Exception) {
    throw e
  }
}
