package org.us.smartbudget

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform