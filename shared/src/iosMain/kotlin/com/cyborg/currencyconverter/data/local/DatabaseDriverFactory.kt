package com.cyborg.currencyconverter.data.local

import com.cyborg.currencyconverter.database.ExchangeRatesDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
  actual fun createDriver(): SqlDriver = NativeSqliteDriver(ExchangeRatesDatabase.Schema, "exchange_rates.db")
}
