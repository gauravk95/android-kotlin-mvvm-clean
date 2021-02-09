package com.gk.android.common

import android.util.Log

private const val TAG = "Artists"

private var debug = true

private var enabled = true

fun disableDebugMessages() {
    debug = false
}

fun enableDebugMessages() {
    debug = true
}

fun disableLog() {
    enabled = false
}

fun enableLog() {
    enabled = true
}

/**
 * Print debug message

 * @param msg - debug message
 */
fun logd(msg: String) {
    if (debug && enabled) {
        Log.d(TAG, getLocation() + msg)
    }
}

/**
 * Print error message

 * @param msg - error message
 */
fun loge(msg: String) {
    if (enabled) Log.e(TAG, getLocation() + msg)
}

/**
 * Print error message using Throwable object

 * @param msg - error message
 * *
 * @param tr - error Throwable object
 */
fun loge(msg: String, tr: Throwable?) {
    if (enabled) Log.e(TAG, getLocation() + msg, tr)
}

/**
 * Get message's root location

 * @return location
 */
private fun getLocation(): String {
    val traces = Thread.currentThread().stackTrace

    val logTraceIdx = traces.indexOfLast { it.fileName == "Log.kt" }
    if (logTraceIdx < traces.size) {
        val trace = traces[logTraceIdx + 1]
        val fileName = trace.fileName.removeSuffix(".kt")
        return "[" + fileName + ":" + trace.methodName + ":" + trace.lineNumber + "]: "
    }
    return "[]: "
}
