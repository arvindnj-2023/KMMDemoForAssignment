package com.aravind.kmmassignment.util

import kotlinx.coroutines.Dispatchers

internal actual fun getDispatcherProvider(): ApiDispatcherProvider = AndroidDispatcherProvider()

private class AndroidDispatcherProvider: ApiDispatcherProvider{
    override val main = Dispatchers.Main
    override val io = Dispatchers.IO
    override val unconfined = Dispatchers.Unconfined
}