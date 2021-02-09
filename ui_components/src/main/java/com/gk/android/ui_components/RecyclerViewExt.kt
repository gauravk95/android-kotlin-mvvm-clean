package com.gk.android.ui_components

import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.gk.android.ui_components.listeners.OnSnapPositionChangeListener
import com.gk.android.ui_components.listeners.SnapOnScrollListener

fun RecyclerView.delayedSmoothScrollToPosition(position: Int, delay: Long) {
    val handler = Handler(context.mainLooper)
    handler.postDelayed({
        smoothScrollToPosition(position)
    }, delay)
}

fun RecyclerView.delayedSmoothScrollToPosition(delay: Long) {
    val handler = Handler(context.mainLooper)
    handler.postDelayed({
        val layoutManager = this.layoutManager as LinearLayoutManager
        when (layoutManager.stackFromEnd) {
            false -> {
                val count = layoutManager.itemCount - 1
                val lastVisible = layoutManager.findLastCompletelyVisibleItemPosition()
                val firstVisible = layoutManager.findFirstCompletelyVisibleItemPosition()
                when (count >= lastVisible - firstVisible) {
                    true -> layoutManager.apply {
                        smoothScrollToPosition(count)
                    }
                    else -> return@postDelayed
                }
            }
            else -> return@postDelayed
        }
    }, delay)
}

fun RecyclerView.updateLayoutManager() {
    val layoutManager = this.layoutManager as LinearLayoutManager
    val count = layoutManager.itemCount - 1
    val lastVisible = layoutManager.findLastCompletelyVisibleItemPosition()
    val firstVisible = layoutManager.findFirstCompletelyVisibleItemPosition()
    if (lastVisible == -1 && firstVisible == -1) return
    when (count == lastVisible - firstVisible) {
        true -> layoutManager.apply { if (stackFromEnd) stackFromEnd = false }
        false -> layoutManager.apply { if (!stackFromEnd) stackFromEnd = true }
    }
}

fun RecyclerView.attachSnapHelperWithListener(
    snapHelper: SnapHelper,
    behavior: SnapOnScrollListener.Behavior = SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL,
    onSnapPositionChangeListener: OnSnapPositionChangeListener
) {
    snapHelper.attachToRecyclerView(this)
    val snapOnScrollListener = SnapOnScrollListener(snapHelper, behavior, onSnapPositionChangeListener)
    addOnScrollListener(snapOnScrollListener)
}