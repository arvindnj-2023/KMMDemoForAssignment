package com.aravind.kmmassignment.util

import kotlinx.coroutines.Dispatchers

internal actual fun getDispatcherProvider(): ApiDispatcherProvider = IosDispatcherProvider()

private class IosDispatcherProvider : ApiDispatcherProvider {
    override val main = Dispatchers.Main
    override val io = Dispatchers.Default
    override val unconfined = Dispatchers.Unconfined
}