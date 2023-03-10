@file:OptIn(DelicateCoroutinesApi::class)

package com.cyborg.currencyconverter.utils.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext

actual val defaultDispatcher: CoroutineDispatcher
  get() = Dispatchers.Default

actual val mainDispatcher: CoroutineDispatcher
  get() = Dispatchers.Main

actual val ioDispatcher: CoroutineDispatcher
  get() = Dispatchers.IO

actual fun singleThreadDispatcher(name: String): CoroutineDispatcher =
  newSingleThreadContext(name)
