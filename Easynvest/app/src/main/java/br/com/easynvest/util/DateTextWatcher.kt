package br.com.easynvest.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class DateTextWatcher(private var editText: EditText, private var mMask: String) : TextWatcher {

    private var isUpdating: Boolean = false
    private var mOldString = ""
    private var before = ""

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        before = s.toString().removeSpecialCharacterInNumber()
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        var str = s.toString().removeSpecialCharacterInNumber()

        if (str.isEmpty()) {
            return
        }

        if (before == 1 && this.before.isNotEmpty() && !isUpdating) {
            val last = this.before.substring(this.before.length, this.before.length)
            if (last.isEmpty()) {
                str = str.substring(0, this.before.length - 1)
            }
        }

        val mask = StringBuilder()
        if (isUpdating) {
            mOldString = str
            isUpdating = false
            return
        }
        var i = 0
        for (m in mMask.toCharArray()) {
            if (m != '#') {
                mask.append(m)
                continue
            }
            try {
                mask.append(str[i])
            } catch (e: Exception) {
                break
            }
            i++
        }
        isUpdating = true
        editText.setText(mask.toString())
        editText.setSelection(mask.length)
    }

    override fun afterTextChanged(s: Editable) {}
}