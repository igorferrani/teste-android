package br.com.easynvest.util

import br.com.easynvest.util.AppConstants.EMPTY
import br.com.easynvest.util.AppConstants.LOCALE
import br.com.easynvest.util.AppConstants.REGEX_SPECIAL_CHARACTER
import java.math.BigDecimal
import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun String.convertDate(from: String, source: String): String {
    var returnDate = EMPTY
    try {
        val formatter = SimpleDateFormat(from, Locale.getDefault())
        formatter.isLenient = false
        val date = formatter.parse(this)
        val formatBR: DateFormat = SimpleDateFormat(source, Locale.getDefault())
        returnDate = formatBR.format(date)
    } catch (e: Exception) {
    }
    return returnDate
}

fun String.convertMonetaryToBigDecimal(): BigDecimal {
    var returnNumber = BigDecimal(0)
    try {
        val replaceable = String.format(
            REGEX_SPECIAL_CHARACTER,
            NumberFormat.getCurrencyInstance(LOCALE).currency.symbol
        )
        val cleanString = this.replace(replaceable.toRegex(), EMPTY)
        returnNumber = BigDecimal(cleanString).setScale(
            2, BigDecimal.ROUND_FLOOR
        ).divide(
            BigDecimal(100), BigDecimal.ROUND_FLOOR
        )
    } catch (e: Exception) {
    }
    return returnNumber
}

fun Double.convertToMoney(): String {
    return NumberFormat.getCurrencyInstance(LOCALE).format(this)
}

fun Double.convertToPercent(): String {
    val mProgressPercentFormat = NumberFormat.getPercentInstance(LOCALE)
    mProgressPercentFormat.maximumFractionDigits = 2
    return mProgressPercentFormat.format(this / 100)
}

fun String.removeSpecialCharacterInNumber(): String {
    return this.toLowerCase().replace(REGEX_SPECIAL_CHARACTER.toRegex(), EMPTY)
}