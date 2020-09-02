package br.com.easyinvest.simulation.usecase

import br.com.easyinvest.simulation.model.RequestSimulation
import br.com.easyinvest.simulation.model.SimulationResult
import br.com.easyinvest.simulation.repository.SimulationRepository

class SimulationUseCase(private val repository: SimulationRepository) {
    suspend fun simulateInvestment(
        investedAmount: Double, maturityDate: String, rate: Double
    ): SimulationResult {
        val requestSimulation = RequestSimulation(
            investedAmount = investedAmount,
            maturityDate = maturityDate,
            rate = rate,
            isTaxFree = false,
            index = "CDI"
        )
        return repository.simulateInvestment(requestSimulation)
    }
}