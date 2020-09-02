package br.com.easyinvest.simulation.state

sealed class SimulationFormInteractor {
    data class ToSimulation(val investedAmount: Double, val maturityDate: String, val rate: Double) :
        SimulationFormInteractor()
}