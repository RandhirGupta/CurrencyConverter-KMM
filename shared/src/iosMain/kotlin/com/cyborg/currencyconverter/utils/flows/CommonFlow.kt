@file:OptIn(DelicateCoroutinesApi::class)

package com.cyborg.currencyconverter.utils.flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

actual open class CommonFlow<T> actual constructor(flow: Flow<T>) : Flow<T> by flow {

  private fun subscribe(
    scope: CoroutineScope,
    dispatcher: CoroutineDispatcher,
    onSubscribe: (T) -> Unit,
  ): DisposableHandle {
    val job = scope.launch(dispatcher) { collect(onSubscribe) }
    return DisposableHandle { job.cancel() }
  }

  fun subscribe(onSubscribe: (T) -> Unit) = subscribe(GlobalScope, Dispatchers.Main, onSubscribe)
}
