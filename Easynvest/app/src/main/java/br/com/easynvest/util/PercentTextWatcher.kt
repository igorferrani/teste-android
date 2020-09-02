package br.com.easynvest.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.lang.ref.WeakReference

class PercentTextWatcher(editText: EditText) : TextWatcher {
    private val editTextWeakReference: WeakReference<EditText> = WeakReference(editText)

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable) {
        val editText = editTextWeakReference.get() ?: return
        editText.removeTextChangedListener(this)
        val cleanValue = editable.toString().removeSpecialCharacterInNumber()
        val numberDouble = if (cleanValue.isNotEmpty()) cleanValue.toDouble() else 0.0
        val formatted = numberDouble.convertToPercent()
        editText.setText(formatted)
        editText.setSelection(formatted.length - 1)
        editText.addTextChangedListener(this)
    }
}