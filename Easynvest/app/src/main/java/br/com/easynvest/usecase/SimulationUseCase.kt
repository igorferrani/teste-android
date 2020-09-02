package br.com.easynvest.usecase

import br.com.easynvest.model.RequestSimulation
import br.com.easynvest.model.SimulationResult
import br.com.easynvest.repository.SimulationRepository
import br.com.easynvest.util.AppConstants
import br.com.easynvest.util.AppConstants.LOCALE
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class SimulationUseCase(private val repository: SimulationRepository) {
    suspend fun simulateInvestment(
        investedAmount: BigDecimal, maturityDate: String, rate: Double
    ): SimulationResult {
        if (investedAmount <= BigDecimal(0))
            throw Exception("Informe um valor de investimento maior que zero")

        if (rate <= 0)
            throw Exception("Informe um percentual maior que zero")

        val requestSimulation = RequestSimulation(
            investedAmount = investedAmount,
            maturityDate = maturityDate,
            rate = rate,
            isTaxFree = false,
            index = "CDI"
        )
        return repository.simulateInvestment(requestSimulation)
    }

    fun isDateValid(dateToValidate: String): Boolean {
        return try {
            val sdf = SimpleDateFormat(AppConstants.BRAZILIAN_DATE_FORMAT, LOCALE)
            sdf.isLenient = false
            val date = sdf.parse(dateToValidate)
            val currentDate = Calendar.getInstance()
            val lastDate = Calendar.getInstance()
            lastDate.add(Calendar.YEAR, 700)
            date.after(currentDate.time) && date.before(lastDate.time)
        } catch (e: Exception) {
            false
        }
    }
}
