package com.gk.android.common.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import org.junit.Rule
import org.junit.rules.TestRule

open class BaseViewModelTest : LiveDataHistoryHolder {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    init {
        mockkLog()
    }
    override val liveDataHistoryMap: HashMap<LiveData<*>, ArrayList<*>> = HashMap()
}