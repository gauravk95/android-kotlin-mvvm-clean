package com.gk.android.data.remote

import androidx.annotation.Keep

@Keep
data class Error(
    val message: String,
    val context: String? = null,
    val requestID: String? = null,
    val code: String? = null
)