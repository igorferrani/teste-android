package br.com.easynvest.simulation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.easynvest.simulation.state.SimulationFormEvent
import br.com.easynvest.simulation.state.SimulationFormInteractor
import br.com.easynvest.simulation.usecase.SimulationUseCase
import kotlinx.coroutines.launch

class SimulationFormViewModel(private val useCase: SimulationUseCase) : ViewModel() {
    private val event = MutableLiveData<SimulationFormEvent>()
    val eventView: LiveData<SimulationFormEvent> = event

    fun interactor(interactor: SimulationFormInteractor) {
        when (interactor) {
            is SimulationFormInteractor.ToSimulation -> toSimulate(
                interactor.investedAmount,
                interactor.maturityDate,
                interactor.rate
            )
        }
    }

    private fun toSimulate(investedAmount: Double, maturityDate: String, rate: Double) {
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
}