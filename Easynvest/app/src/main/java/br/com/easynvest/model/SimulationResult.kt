package br.com.easynvest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SimulationResult(
    val investmentParameter: InvestmentParameter,
    val grossAmount: Double,
    val taxesAmount: Double,
    val netAmount: Double,
    val grossAmountProfit: Double,
    val netAmountProfit: Double,
    val annualGrossRateProfit: Double,
    val monthlyGrossRateProfit: Double,
    val dailyGrossRateProfit: Double,
    val taxesRate: Double,
    val rateProfit: Double,
    val annualNetRateProfit: Double
) : Parcelable
