package com.gk.android.common.test

import android.util.Log
import io.mockk.MockKVerificationScope
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify

fun coVerifyNever(verifyBlock: suspend MockKVerificationScope.() -> Unit) {
    return coVerify(inverse = true, verifyBlock = verifyBlock)
}

fun verifyNever(verifyBlock: MockKVerificationScope.() -> Unit) {
    return verify(inverse = true, verifyBlock = verifyBlock)
}

fun mockkLog() {
    mockkStatic(Log::class)

    every {
        Log.d(any(), any())
    } answers {
        println("${this.arg<String>(0)} ${this.arg<String>(1)}")
        0
    }

    every {
        Log.e(any(), any(), any())
    } answers {
        println("${this.arg<String>(0)} ${this.arg<String>(1)}\n${this.arg<String>(2)}")
        0
    }
}