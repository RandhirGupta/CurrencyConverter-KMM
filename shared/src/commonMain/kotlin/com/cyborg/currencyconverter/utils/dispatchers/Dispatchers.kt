package com.cyborg.currencyconverter.utils.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

expect val defaultDispatcher: CoroutineDispatcher

expect val mainDispatcher: CoroutineDispatcher

expect val ioDispatcher: CoroutineDispatcher

expect fun singleThreadDispatcher(name: String): CoroutineDispatcher
