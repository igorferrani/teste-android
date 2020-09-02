package br.com.easyinvest.simulation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.easyinvest.simulation.usecase.SimulationUseCase

class SimulationFormViewModelFactory(private val useCase: SimulationUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SimulationUseCase::class.java).newInstance(useCase)
    }
}