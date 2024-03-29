package com.cyborg.currencyconverter.data.local

import android.content.Context
import com.cyborg.currencyconverter.database.ExchangeRatesDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {

  actual fun createDriver(): SqlDriver {
    return AndroidSqliteDriver(ExchangeRatesDatabase.Schema, context, "exchange_rates.db")
  }
}
