package com.aravind.kmmassignment

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform