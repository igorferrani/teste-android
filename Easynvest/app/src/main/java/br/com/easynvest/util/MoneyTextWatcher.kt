package br.com.easynvest.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import br.com.easynvest.util.AppConstants.LOCALE
import java.lang.ref.WeakReference
import java.text.NumberFormat

class MoneyTextWatcher(editText: EditText) : TextWatcher {
    private val editTextWeakReference: WeakReference<EditText> = WeakReference(editText)

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    override fun afterTextChanged(editable: Editable) {
        val editText = editTextWeakReference.get() ?: return
        editText.removeTextChangedListener(this)
        val parsed = editable.toString().convertMonetaryToBigDecimal()
        val formatted = NumberFormat.getCurrencyInstance(LOCALE).format(parsed)
        editText.setText(formatted)
        editText.setSelection(formatted.length)
        editText.addTextChangedListener(this)
    }
}