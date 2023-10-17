package com.aravind.kmmassignment.util

import kotlinx.coroutines.CoroutineDispatcher

/**
 * A Dispatcher abstraction in order to ease testing coroutines
 */
interface ApiDispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}

internal expect fun getDispatcherProvider(): ApiDispatcherProvider