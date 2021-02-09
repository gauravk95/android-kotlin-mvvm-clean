package com.gk.android.ui_components

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/**
 * Extension function to set the toolbar
 */
fun AppCompatActivity.setToolbar(toolbar: Toolbar, @StringRes titleId: Int) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
    supportActionBar?.title = getString(titleId)
}