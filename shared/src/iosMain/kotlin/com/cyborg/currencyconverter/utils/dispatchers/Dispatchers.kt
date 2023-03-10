@file:OptIn(InternalCoroutinesApi::class, ExperimentalCoroutinesApi::class)

package com.cyborg.currencyconverter.utils.dispatchers

import kotlinx.coroutines.*
import platform.darwin.*
import kotlin.coroutines.CoroutineContext

actual val defaultDispatcher: CoroutineDispatcher
  get() = MainLoopDispatcher

actual val mainDispatcher: CoroutineDispatcher
  get() = MainLoopDispatcher

actual val ioDispatcher: CoroutineDispatcher
  get() = MainLoopDispatcher

actual fun singleThreadDispatcher(name: String): CoroutineDispatcher =
  MainLoopDispatcher

private object MainLoopDispatcher : CoroutineDispatcher(), Delay {

  override fun dispatch(context: CoroutineContext, block: Runnable) {
    dispatch_async(dispatch_get_main_queue()) { block.run() }
  }

  override fun scheduleResumeAfterDelay(timeMillis: Long, continuation: CancellableContinuation<Unit>) {
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, timeMillis * 1_000_000), dispatch_get_main_queue()) {
      with(continuation) {
        resumeUndispatched(Unit)
      }
    }
  }

  override fun invokeOnTimeout(timeMillis: Long, block: Runnable, context: CoroutineContext): DisposableHandle {
    val handle = object : DisposableHandle {
      var disposed = false
        private set

      override fun dispose() {
        disposed = true
      }
    }

    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, timeMillis * 1_000_000), dispatch_get_main_queue()) {
      if (!handle.disposed) {
        block.run()
      }
    }

    return handle
  }
}
