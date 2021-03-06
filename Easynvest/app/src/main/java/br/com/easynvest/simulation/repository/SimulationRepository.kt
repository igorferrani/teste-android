package br.com.easynvest.simulation.repository

import br.com.easynvest.simulation.model.RequestSimulation
import br.com.easynvest.simulation.model.SimulationResult
import br.com.easynvest.simulation.repository.remote.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SimulationRepository {
    suspend fun simulateInvestment(
        requestSimulation: RequestSimulation
    ): SimulationResult = withContext(Dispatchers.IO) {
        try {
            return@withContext Service.service.toSimulate(
                requestSimulation.investedAmount.toString(),
                requestSimulation.index,
                requestSimulation.rate.toString(),
                requestSimulation.isTaxFree.toString(),
                requestSimulation.maturityDate
            )
        } catch (exception: Exception) {
            throw exception
        }
    }
}