package com.gk.android.common.eventbus

import androidx.lifecycle.LiveData

class EventLiveData(@param:LiveDataBus.Subject private val subject: Int) : LiveData<Any?>() {
    fun update(obj: Any?) {
        postValue(obj)
    }
}