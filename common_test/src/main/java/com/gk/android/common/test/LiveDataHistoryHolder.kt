package com.gk.android.common.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.junit.Assert.assertEquals
import kotlin.reflect.KProperty1
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.jvmErasure

interface LiveDataHistoryHolder {
    val liveDataHistoryMap: HashMap<LiveData<*>, ArrayList<*>>

    fun <T> LiveData<T>.keepHistory() {
        this.observeForever { changedValue ->
            @Suppress("UNCHECKED_CAST")
            val historyList = liveDataHistoryMap[this] as ArrayList<T?>? ?: ArrayList()
            historyList.add(changedValue)
            liveDataHistoryMap[this] = historyList
        }
    }

    fun <T> LiveData<T>.history(): List<T?> {
        assertObserver()
        @Suppress("UNCHECKED_CAST")
        return liveDataHistoryMap[this] as List<T?>? ?: emptyList()
    }

    fun <T> LiveData<T>.single(): T? {
        val history = history()
        assertEquals("History should contain only single element", 1, history.size)
        return history[0]
    }

    fun <T> LiveData<T>.resetHistory() {
        assertObserver()
        @Suppress("UNCHECKED_CAST")
        liveDataHistoryMap.remove(this)
    }

    fun LiveData<*>.hits(): Int {
        return history().size
    }

    fun ViewModel.keepHistory() {
        getAllLiveDataProperties().forEach {
            it.isAccessible = true
            (it.get(this) as LiveData<*>).keepHistory()
        }
    }

    fun ViewModel.resetHistory() {
        getAllLiveDataProperties().forEach {
            (it.get(this) as LiveData<*>).resetHistory()
        }
    }

    private fun ViewModel.getAllLiveDataProperties(): List<KProperty1<ViewModel, *>> {
        return this.javaClass.kotlin.memberProperties.filter {
            it.returnType.jvmErasure.isSubclassOf(LiveData::class)
        }
    }

    private fun LiveData<*>.assertObserver() {
        if (!hasObservers()) {
            throw IllegalStateException(
                "Call 'keepHistory()' for this event in " +
                    "setUp method"
            )
        }
    }
}