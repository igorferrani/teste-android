package br.com.easynvest.model

import java.math.BigDecimal

data class RequestSimulation(
    val investedAmount: BigDecimal,
    val index: String,
    val rate: Double,
    val isTaxFree: Boolean,
    val maturityDate: String
)
