package br.com.easynvest.repository

import br.com.easynvest.model.RequestSimulation
import br.com.easynvest.model.SimulationResult
import br.com.easynvest.repository.remote.Service

class SimulationRepository {
    suspend fun simulateInvestment(
        requestSimulation: RequestSimulation
    ): SimulationResult {
        try {
            return Service.service.toSimulate(
                requestSimulation.investedAmount.toString(),
                requestSimulation.index,
                requestSimulation.rate.toString(),
                requestSimulation.isTaxFree.toString(),
                requestSimulation.maturityDate
            )
        } catch (exception: Exception) {
            throw Exception("Erro ao realizar a simulação. Tente novamente.")
        }
    }
}
