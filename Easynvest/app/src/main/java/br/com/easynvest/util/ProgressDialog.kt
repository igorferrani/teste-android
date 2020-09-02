package br.com.easynvest.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout

class ProgressDialog(context: Context) {
    private var dialog: Dialog = Dialog(context)
    private val progressBar: ProgressBar
    fun show() {
        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    fun dismiss() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }

    init {
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        val relativeLayout = RelativeLayout(context)
        val layoutParams =
            RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        relativeLayout.layoutParams = layoutParams
        progressBar = ProgressBar(context)
        val layoutParamsProgress =
            RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParamsProgress.addRule(RelativeLayout.CENTER_IN_PARENT)
        val linearlayoutParamsProgress =
            LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        linearlayoutParamsProgress.gravity = Gravity.CENTER
        progressBar.layoutParams = layoutParamsProgress
        relativeLayout.addView(progressBar)
        dialog.window!!.setContentView(relativeLayout, layoutParams)
        dialog.window!!.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )
    }
}