package br.com.easynvest.usecase

import br.com.easynvest.model.RequestSimulation
import br.com.easynvest.model.SimulationResult
import br.com.easynvest.repository.SimulationRepository
import br.com.easynvest.util.AppConstants
import com.google.gson.Gson
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class SimulationUseCaseTest {

    @MockK
    lateinit var repository: SimulationRepository

    @MockK
    var mockCalendar = Calendar.getInstance()

    private lateinit var useCase: SimulationUseCase

    private fun mockSimulationResult(): SimulationResult {
        val gson = Gson()
        return gson.fromJson(
            "{\n" +
                    "    \"investmentParameter\": {\n" +
                    "        \"investedAmount\": 32323.0,                      \n" +
                    "        \"yearlyInterestRate\": 9.5512,                   \n" +
                    "        \"maturityTotalDays\": 1981,                      \n" +
                    "        \"maturityBusinessDays\": 1409,                   \n" +
                    "        \"maturityDate\": \"2023-03-03T00:00:00\",        \n" +
                    "        \"rate\": 123.0,                                  \n" +
                    "        \"isTaxFree\": false                              \n" +
                    "    },\n" +
                    "    \"grossAmount\": 60528.20,                            \n" +
                    "    \"taxesAmount\": 4230.78,                             \n" +
                    "    \"netAmount\": 56297.42,                              \n" +
                    "    \"grossAmountProfit\": 28205.20,                      \n" +
                    "    \"netAmountProfit\": 23974.42,                        \n" +
                    "    \"annualGrossRateProfit\": 87.26,                     \n" +
                    "    \"monthlyGrossRateProfit\": 0.76,                     \n" +
                    "    \"dailyGrossRateProfit\": 0.000445330025305748,       \n" +
                    "    \"taxesRate\": 15.0,                                  \n" +
                    "    \"rateProfit\": 9.5512,                               \n" +
                    "    \"annualNetRateProfit\": 74.17                        \n" +
                    "}", SimulationResult::class.java
        )
    }

    private fun mockRequestSimulation(): RequestSimulation {
        return RequestSimulation(
            investedAmount = BigDecimal(1.0),
            index = "CDI",
            rate = 1.0,
            isTaxFree = false,
            maturityDate = ""
        )
    }

    @Before
    fun prepareTest() {
        MockKAnnotations.init(this)
        coEvery { repository.simulateInvestment(mockRequestSimulation()) } returns mockSimulationResult()
        useCase = SimulationUseCase(repository)
    }

    @Test
    fun `simulateInvestment - ao chamar o metodo, deve retornar o objeto com o resultado da simulacao`() {
        runBlocking {
            Assert.assertEquals(useCase.simulateInvestment(BigDecimal(1.0), "", 1.0), mockSimulationResult())
        }
    }

    @Test
    fun `isDateValid - ao chamar o metodo passando data, deve retornar data valida`() {
        val sdf = SimpleDateFormat(AppConstants.BRAZILIAN_DATE_FORMAT, AppConstants.LOCALE)
        sdf.isLenient = false
        val mockDateCurrent = Date()
        every { mockCalendar.time } returns GregorianCalendar(2020, 1, 1).time

        Assert.assertEquals(useCase.isDateValid("01/06/2020"), true)
    }
}
