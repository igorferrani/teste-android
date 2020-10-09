package br.com.easynvest.simulation.state

import br.com.easynvest.simulation.model.SimulationResult

sealed class SimulationFormEvent {
    data class Error(val message: String) : SimulationFormEvent()
    data class SimulationResultSuccess(val simulation: SimulationResult) : SimulationFormEvent()
    object ShowLoading : SimulationFormEvent()
    object HideLoading : SimulationFormEvent()
}