package com.cyborg.currencyconverter.android.di

import android.content.Context
import com.cyborg.currencyconverter.data.local.DatabaseDriverFactory
import com.cyborg.currencyconverter.data.local.ExchangeRatesLocalDataSource
import com.cyborg.currencyconverter.data.poller.ExchangeRatesPollerImpl
import com.cyborg.currencyconverter.data.remote.datasource.exchangerates.ExchangeRatesDataSource
import com.cyborg.currencyconverter.data.repo.ExchangeRatesRepoImpl
import com.cyborg.currencyconverter.database.ExchangeRatesDatabase
import com.cyborg.currencyconverter.domain.poller.ExchangeRatesPoller
import com.cyborg.currencyconverter.domain.repo.ExchangeRatesRepo
import com.cyborg.currencyconverter.domain.usecases.FetchExchangeRatesUseCase
import com.cyborg.currencyconverter.domain.usecases.GetExchangeRatesUseCase
import com.cyborg.currencyconverter.utils.dispatchers.ioDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

  @Provides
  fun providesExchangeRatesDataSource(): ExchangeRatesDataSource = ExchangeRatesDataSource()

  @Provides
  @Singleton
  fun providesDatabaseFactory(
    @ApplicationContext context: Context,
  ): DatabaseDriverFactory = DatabaseDriverFactory(context)

  @Provides
  @Singleton
  fun providesExchangeRatesDatabase(
    factory: DatabaseDriverFactory,
  ): ExchangeRatesDatabase = ExchangeRatesDatabase(factory.createDriver())

  @Provides
  @Singleton
  fun providesExchangeRatesLocalDataSource(
    database: ExchangeRatesDatabase,
  ): ExchangeRatesLocalDataSource = ExchangeRatesLocalDataSource(database)

  @Provides
  @Singleton
  fun providesExchangeRatesRepo(
    dataSource: ExchangeRatesDataSource,
    localDataSource: ExchangeRatesLocalDataSource,
  ): ExchangeRatesRepo = ExchangeRatesRepoImpl(dataSource, localDataSource)

  @Provides
  fun providesFetchExchangeRatesUseCase(
    exchangeRatesRepo: ExchangeRatesRepo,
  ): FetchExchangeRatesUseCase = FetchExchangeRatesUseCase(exchangeRatesRepo)

  @Provides
  fun providesGetExchangeRatesUseCase(
    exchangeRatesRepo: ExchangeRatesRepo,
  ): GetExchangeRatesUseCase = GetExchangeRatesUseCase(exchangeRatesRepo)

  @Provides
  fun providesExchangeRatesPoller(
    fetchExchangeRatesUseCase: FetchExchangeRatesUseCase,
  ): ExchangeRatesPoller = ExchangeRatesPollerImpl(fetchExchangeRatesUseCase, ioDispatcher)
}
