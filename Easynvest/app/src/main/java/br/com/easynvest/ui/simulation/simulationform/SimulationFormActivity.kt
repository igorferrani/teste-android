package br.com.easynvest.ui.simulation.simulationform

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.easynvest.R
import br.com.easynvest.model.SimulationResult
import br.com.easynvest.repository.SimulationRepository
import br.com.easynvest.ui.simulation.simulationform.state.SimulationFormEvent
import br.com.easynvest.ui.simulation.simulationform.viewmodel.SimulationFormViewModel
import br.com.easynvest.ui.simulation.simulationform.viewmodel.SimulationFormViewModelFactory
import br.com.easynvest.ui.simulation.simulationresult.SimulationResultActivity
import br.com.easynvest.usecase.SimulationUseCase
import br.com.easynvest.util.AppConstants.AMERICAN_DATE_FORMAT
import br.com.easynvest.util.AppConstants.BRAZILIAN_DATE_FORMAT
import br.com.easynvest.util.DateTextWatcher
import br.com.easynvest.util.MoneyTextWatcher
import br.com.easynvest.util.PercentTextWatcher
import br.com.easynvest.util.ProgressDialog
import br.com.easynvest.util.convertDate
import br.com.easynvest.util.convertMonetaryToBigDecimal
import br.com.easynvest.util.removeSpecialCharacterInNumber
import com.google.android.material.snackbar.Snackbar

class SimulationFormActivity : AppCompatActivity() {
    private lateinit var viewModel: SimulationFormViewModel

    private lateinit var loading: ProgressDialog
    private lateinit var etInvestedAmount: EditText
    private lateinit var etMaturityDate: EditText
    private lateinit var etRate: EditText
    private lateinit var btnToSimulate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulator_form)

        initVariables()
        initViewModel()
        initView()
    }

    private fun initVariables() {
        loading = ProgressDialog(this)
    }

    private fun initViewModel() {
        val repository = SimulationRepository()
        val useCase = SimulationUseCase(repository)
        val factory = SimulationFormViewModelFactory(useCase)
        viewModel = ViewModelProvider(this, factory).get(SimulationFormViewModel::class.java)
        initObservable()
    }

    private fun initView() {
        btnToSimulate.setOnClickListener {
            toSimulate()
        }
        etInvestedAmount = findViewById(R.id.et_invested_amount)
        etInvestedAmount.addTextChangedListener(MoneyTextWatcher(etInvestedAmount))
        etInvestedAmount.addTextChangedListener {
            validateFormAndModifyButtonState()
        }
        etMaturityDate = findViewById(R.id.et_maturity_date)
        etMaturityDate.addTextChangedListener(DateTextWatcher(etMaturityDate, "##/##/####"))
        etMaturityDate.addTextChangedListener {
            validateFormAndModifyButtonState()
        }
        etRate = findViewById(R.id.et_rate)
        etRate.addTextChangedListener(PercentTextWatcher(etRate))
        etRate.addTextChangedListener {
            validateFormAndModifyButtonState()
        }
    }

    private fun initObservable() {
        viewModel.eventView.observe(this, Observer {
            when (it) {
                is SimulationFormEvent.Error -> showError(it.message)
                is SimulationFormEvent.SimulationResultSuccess -> successSimulation(it.simulation)
                is SimulationFormEvent.ShowLoading -> showLoading()
                is SimulationFormEvent.HideLoading -> hideLoading()
            }
        })
    }

    private fun toSimulate() {
        val investedAmount = etInvestedAmount.text.toString().convertMonetaryToBigDecimal()
        val maturityDate = etMaturityDate.text.toString().convertDate(BRAZILIAN_DATE_FORMAT, AMERICAN_DATE_FORMAT)
        val rate = etRate.text.toString().removeSpecialCharacterInNumber().toDouble()

        viewModel.toSimulate(investedAmount, maturityDate, rate)
    }

    private fun validateFormAndModifyButtonState() {
        btnToSimulate.isEnabled =
            validateFormIsNotEmpty() && viewModel.isDateValid(etMaturityDate.text.toString())
    }

    private fun validateFormIsNotEmpty(): Boolean {
        val investedAmount = etInvestedAmount.text.toString()
        val maturityDate = etMaturityDate.text.toString()
        val rate = etRate.text.toString()
        return viewModel.validateFormIsNotEmpty(investedAmount, maturityDate, rate)
    }

    private fun showError(message: String) {
        Snackbar.make(findViewById(R.id.view_root), message, Snackbar.LENGTH_LONG).show()
    }

    private fun showLoading() {
        loading.show()
    }

    private fun hideLoading() {
        loading.dismiss()
    }

    private fun successSimulation(simulation: SimulationResult) {
        startActivity(
            SimulationResultActivity.newIntent(
                this,
                simulation
            )
        )
    }
}
