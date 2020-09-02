package br.com.easynvest.ui.simulation.simulationform.state

import br.com.easynvest.model.SimulationResult

sealed class SimulationFormEvent {
    data class Error(val message: String) : SimulationFormEvent()
    data class SimulationResultSuccess(val simulation: SimulationResult) : SimulationFormEvent()
    object ShowLoading : SimulationFormEvent()
    object HideLoading : SimulationFormEvent()
}