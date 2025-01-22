package com.straccion.security

import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

private val ALGORITHM = System.getenv("hash.algorithm")
private val HASH_KEY = System.getenv("hash.secret").toByteArray()
private val hMacKEy = SecretKeySpec(HASH_KEY, ALGORITHM)

fun hashPassword(password: String): String {
    val hMac = Mac.getInstance(ALGORITHM)
    hMac.init(hMacKEy)

    return hex(hMac.doFinal(password.toByteArray()))
}