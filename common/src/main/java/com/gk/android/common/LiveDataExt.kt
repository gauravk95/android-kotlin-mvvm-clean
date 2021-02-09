package com.gk.android.common

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.setIfDifferent(newValue: T) {
    if (value != newValue) value = newValue
}

fun <T> MutableLiveData<T>.set(newValue: T) {
    value = newValue
}

fun LiveEvent<Unit>.call() {
    value = Unit
}

fun <T> LiveEvent<T>.call(newValue: T) {
    value = newValue
}