package com.gk.android.ui_components.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.gk.android.ui_components.R

class SystemDialog : DialogFragment() {

    private val title by lazy {
        arguments?.getInt(titleExtra)
            ?: throw IllegalArgumentException("Title not included when creating dialog")
    }

    private val body: Int by lazy {
        arguments?.getInt(bodyExtra)
            ?: throw IllegalArgumentException("Body not included when creating dialog")
    }

    private val okButtonCopy by lazy {
        arguments?.getInt(okButtonCopyExtra)
            ?: throw IllegalArgumentException("Ok button not included when creating dialog")
    }

    private val cancelButtonCopy by lazy {
        arguments?.getInt(cancelButtonCopyExtra)
            ?: throw IllegalArgumentException("Cancel button not included when creating dialog")
    }

    lateinit var action: () -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!::action.isInitialized) dismiss()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext()).apply {
            setTitle(title)
            if (body != -1) setMessage(body)
            setPositiveButton(okButtonCopy) { _, _ ->
                action()
            }
            setNegativeButton(cancelButtonCopy) { _, _ -> }
        }.create()
    }

    companion object {
        const val titleExtra = "titleExtra"
        const val bodyExtra = "bodyExtra"
        const val okButtonCopyExtra = "okButtonCopyExtra"
        const val cancelButtonCopyExtra = "cancelButtonCopyExtra"

        fun show(
            fragmentManager: FragmentManager?,
            @StringRes title: Int,
            @StringRes body: Int = -1,
            @StringRes okButtonCopy: Int = R.string.system_dialog_btn_ok,
            @StringRes cancelButtonCopy: Int = R.string.system_dialog_btn_cancel,
            action: () -> Unit
        ) {
            fragmentManager?.let {
                val arguments = bundleOf(
                    titleExtra to title,
                    bodyExtra to body,
                    okButtonCopyExtra to okButtonCopy,
                    cancelButtonCopyExtra to cancelButtonCopy
                )
                val systemDialog = SystemDialog()
                systemDialog.action = action
                systemDialog.arguments = arguments
                systemDialog.show(it, SystemDialog::class.java.simpleName)
            }
        }
    }
}
