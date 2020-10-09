package br.com.easynvest.simulation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.easynvest.simulation.usecase.SimulationUseCase

class SimulationFormViewModelFactory(private val useCase: SimulationUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SimulationUseCase::class.java).newInstance(useCase)
    }
}