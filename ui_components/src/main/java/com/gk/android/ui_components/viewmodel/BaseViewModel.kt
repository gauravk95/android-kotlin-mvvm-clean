package com.gk.android.ui_components.viewmodel

import androidx.lifecycle.ViewModel
import com.gk.android.common.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {
    // use it to send message toast message
    val toastMsg = SingleLiveEvent<Int>()
}