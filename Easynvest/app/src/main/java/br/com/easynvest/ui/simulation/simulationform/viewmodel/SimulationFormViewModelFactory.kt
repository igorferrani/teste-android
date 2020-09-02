package br.com.easynvest.ui.simulation.simulationform.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.easynvest.usecase.SimulationUseCase

class SimulationFormViewModelFactory(private val useCase: SimulationUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SimulationUseCase::class.java).newInstance(useCase)
    }
}