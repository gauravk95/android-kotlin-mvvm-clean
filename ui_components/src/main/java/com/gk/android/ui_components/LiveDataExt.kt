package com.gk.android.ui_components

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

/**
 * Sets lambda to observe NON nullable value changes in [Fragment]
 */
fun <T> LiveData<T>.bind(fragment: Fragment, observer: (T) -> Unit) {
    // Ignore coming null values
    this.observe(fragment.viewLifecycleOwner, { it?.run { observer(it) } })
}

/**
 * Sets lambda to observe NON nullable value changes in [AppCompatActivity]
 */
fun <T> LiveData<T>.bind(activity: AppCompatActivity, observer: (T) -> Unit) {
    // Ignore coming null values
    this.observe(activity, { it?.run { observer(it) } })
}

/**
 * Sets lambda to observe NON nullable value changes in [LifecycleOwner]
 */
fun <T> LiveData<T>.bind(lifecycle: LifecycleOwner, observer: (T) -> Unit) {
    // Ignore coming null values
    this.observe(lifecycle, { it?.run { observer(it) } })
}