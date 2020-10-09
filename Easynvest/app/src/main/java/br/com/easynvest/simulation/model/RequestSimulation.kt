package br.com.easynvest.simulation.model

data class RequestSimulation(
    val investedAmount: Double,
    val index: String,
    val rate: Double,
    val isTaxFree: Boolean,
    val maturityDate: String
)