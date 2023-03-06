package com.cyborg.currencyconverter

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform