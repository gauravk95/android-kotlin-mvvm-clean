package com.gk.android.common.eventbus

import android.util.SparseArray
import androidx.annotation.IntDef
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

/**
 * A Helper class to send and receive events globally
 */
object LiveDataBus {

    private val subjectMap = SparseArray<EventLiveData>()

    /**
     *  Subject definitions that can be used
     *
     *  NOTE: If a new subject is added, don't forget to [IntDef] in [Subject]
     */
    const val SUBJECT_END_CALL = 0
    const val SUBJECT_MESSAGE_SENT_STATE = 1

    /**
     * Get the live data or create it if it's not already in memory.
     *
     * [makeNew] true - create if doesn't exists, false return existing
     */
    private fun getLiveData(@Subject subjectCode: Int, makeNew: Boolean = true): EventLiveData? {
        var liveData = subjectMap[subjectCode]
        if (liveData == null && makeNew) {
            liveData = EventLiveData(subjectCode)
            subjectMap.put(subjectCode, liveData)
        }
        return liveData
    }

    /**
     * Subscribe to the specified subject and listen for updates on that subject.
     */
    fun subscribe(@Subject subject: Int, lifecycle: LifecycleOwner, action: Observer<Any?>) {
        getLiveData(subject)?.observe(lifecycle, action)
    }

    /**
     * Removes this subject when it has no observers.
     */
    fun unregister(@Subject subject: Int) {
        subjectMap.remove(subject)
    }

    /**
     * Publish an object to the specified subject for all subscribers of that subject.
     *
     * @return true if published to an active subscriber, else false
     */
    fun publish(@Subject subject: Int, message: Any): Boolean {
        val liveData = getLiveData(subject, false) ?: return false
        liveData.update(message)
        return true
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(SUBJECT_END_CALL, SUBJECT_MESSAGE_SENT_STATE)
    internal annotation class Subject
}