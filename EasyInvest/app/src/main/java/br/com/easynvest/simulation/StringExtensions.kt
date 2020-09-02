package br.com.easyinvest.simulation

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun String.convertDate(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    formatter.isLenient = false
    val date = formatter.parse(this)
    val formatBR: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatBR.format(date)
}