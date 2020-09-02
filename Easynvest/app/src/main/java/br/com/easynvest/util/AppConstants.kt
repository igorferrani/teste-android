package br.com.easynvest.util

import java.util.Locale

object AppConstants {
    const val AMERICAN_DATE_TIME_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss"
    const val AMERICAN_DATE_FORMAT: String = "yyyy-MM-dd"
    const val BRAZILIAN_DATE_FORMAT: String = "dd/MM/yyyy"
    val LOCALE: Locale = Locale("pt", "BR")
    const val EMPTY: String = ""
    const val REGEX_SPECIAL_CHARACTER: String = "[^\\d]"
}