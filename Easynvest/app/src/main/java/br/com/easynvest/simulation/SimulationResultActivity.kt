package br.com.easynvest.simulation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.easynvest.simulation.model.SimulationResult
import br.com.easynvest.R

class SimulationResultActivity : AppCompatActivity() {
    private lateinit var simulation: SimulationResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulator_result)

        initView()
        getExtras()
        bindView()
    }

    private fun initView() {
        findViewById<Button>(R.id.btn_simulate_again).setOnClickListener {
            simulateAgain()
        }
    }

    private fun getExtras() {
        simulation = (intent.extras?.getParcelable(EXTRA_RESULT_SIMULATION) ?: return)
    }

    private fun bindView() {
        findViewById<TextView>(R.id.tv_gross_amount).text = simulation.grossAmount.toString()
        findViewById<TextView>(R.id.tv_gross_amount_profit).text = simulation.grossAmountProfit.toString()
        findViewById<TextView>(R.id.tv_invested_amount_detail_value).text =
            simulation.investmentParameter.investedAmount.toString()
        findViewById<TextView>(R.id.tv_gross_amount_detail_value).text = simulation.grossAmount.toString()
        findViewById<TextView>(R.id.tv_gross_amount_profit_detail_value).text = simulation.grossAmountProfit.toString()
        findViewById<TextView>(R.id.tv_taxes_detail_value).text = resources.getString(
            R.string.simulation_result_taxes_detail_value,
            simulation.taxesAmount.toString(),
            simulation.taxesRate.toString()
        )
        findViewById<TextView>(R.id.tv_net_amount_detail_value).text = simulation.netAmount.toString()
        findViewById<TextView>(R.id.tv_maturity_date_detail_value).text =
            simulation.investmentParameter.maturityDate
        findViewById<TextView>(R.id.tv_maturity_total_date_detail_value).text =
            simulation.investmentParameter.maturityTotalDays.toString()
        findViewById<TextView>(R.id.tv_monthly_gross_rate_profit_detail_value).text =
            simulation.monthlyGrossRateProfit.toString()
        findViewById<TextView>(R.id.tv_rate_detail_value).text = simulation.investmentParameter.rate.toString()
        findViewById<TextView>(R.id.tv_yearly_interest_rate_detail_value).text =
            simulation.investmentParameter.yearlyInterestRate.toString()
        findViewById<TextView>(R.id.tv_annual_gross_rate_profit_detail_value).text =
            simulation.annualGrossRateProfit.toString()
    }

    private fun simulateAgain() {
        finish()
    }

    companion object {
        const val EXTRA_RESULT_SIMULATION: String = "EXTRA_RESULT_SIMULATION"

        fun newIntent(context: Context, simulation: SimulationResult): Intent {
            val intent = Intent(context, SimulationResultActivity::class.java)
            intent.putExtra(EXTRA_RESULT_SIMULATION, simulation)
            return intent
        }
    }
}
