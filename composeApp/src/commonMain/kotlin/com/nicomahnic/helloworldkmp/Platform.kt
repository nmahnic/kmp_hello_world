package com.nicomahnic.helloworldkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform