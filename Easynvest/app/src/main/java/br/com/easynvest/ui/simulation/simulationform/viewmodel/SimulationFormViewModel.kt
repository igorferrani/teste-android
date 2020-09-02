package br.com.easynvest.ui.simulation.simulationform.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.easynvest.ui.simulation.simulationform.state.SimulationFormEvent
import br.com.easynvest.usecase.SimulationUseCase
import kotlinx.coroutines.launch
import java.math.BigDecimal

class SimulationFormViewModel(private val useCase: SimulationUseCase) : ViewModel() {
    private val event = MutableLiveData<SimulationFormEvent>()
    val eventView: LiveData<SimulationFormEvent> = event

    fun toSimulate(investedAmount: BigDecimal, maturityDate: String, rate: Double) {
        viewModelScope.launch {
            event.value = SimulationFormEvent.ShowLoading
            try {
                val result = useCase.simulateInvestment(investedAmount, maturityDate, rate)
                event.value = SimulationFormEvent.SimulationResultSuccess(result)
            } catch (exception: Exception) {
                event.value = SimulationFormEvent.Error(exception.message.toString())
            }
            event.value = SimulationFormEvent.HideLoading
        }
    }

    fun isDateValid(date: String): Boolean {
        return useCase.isDateValid(date)
    }

    fun validateFormIsNotEmpty(investedAmount: String, maturityDate: String, rate: String): Boolean {
        return investedAmount.isNotEmpty() && maturityDate.isNotEmpty() && rate.isNotEmpty()
    }
}
