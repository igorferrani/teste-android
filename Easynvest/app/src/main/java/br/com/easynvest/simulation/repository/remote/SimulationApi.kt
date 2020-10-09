package br.com.easynvest.simulation.repository.remote

import br.com.easynvest.simulation.model.SimulationResult
import retrofit2.http.GET
import retrofit2.http.Query

interface SimulationApi {
    @GET("calculator/simulate")
    suspend fun toSimulate(
        @Query("investedAmount") investedAmount: String,
        @Query("index") index: String,
        @Query("rate") rate: String,
        @Query("isTaxFree") isTaxFree: String,
        @Query("maturityDate") maturityDate: String
    ): SimulationResult
}