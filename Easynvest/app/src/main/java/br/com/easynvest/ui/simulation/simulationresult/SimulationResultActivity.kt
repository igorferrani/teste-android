package br.com.easynvest.ui.simulation.simulationresult

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.easynvest.R
import br.com.easynvest.util.AppConstants.AMERICAN_DATE_TIME_FORMAT
import br.com.easynvest.util.AppConstants.BRAZILIAN_DATE_FORMAT
import br.com.easynvest.util.convertDate
import br.com.easynvest.util.convertToMoney
import br.com.easynvest.util.convertToPercent
import br.com.easynvest.model.SimulationResult

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
        findViewById<TextView>(R.id.tv_gross_amount).text = simulation.grossAmount.convertToMoney()
        findViewById<TextView>(R.id.tv_gross_amount_profit).setText(
            getSpannableGrossAmountProfit(simulation.grossAmountProfit.convertToMoney()),
            TextView.BufferType.SPANNABLE
        )
        findViewById<TextView>(R.id.tv_invested_amount_detail_value).text =
            simulation.investmentParameter.investedAmount.convertToMoney()
        findViewById<TextView>(R.id.tv_gross_amount_detail_value).text =
            simulation.grossAmount.convertToMoney()
        findViewById<TextView>(R.id.tv_gross_amount_profit_detail_value).text =
            simulation.grossAmountProfit.convertToMoney()
        findViewById<TextView>(R.id.tv_taxes_detail_value).text = resources.getString(
            R.string.simulation_result_taxes_detail_value,
            simulation.taxesAmount.convertToMoney(),
            simulation.taxesRate.convertToPercent()
        )
        findViewById<TextView>(R.id.tv_net_amount_detail_value).text =
            simulation.netAmount.convertToMoney()

        findViewById<TextView>(R.id.tv_maturity_date_detail_value).text =
            simulation.investmentParameter.maturityDate.convertDate(AMERICAN_DATE_TIME_FORMAT, BRAZILIAN_DATE_FORMAT)
        findViewById<TextView>(R.id.tv_maturity_total_date_detail_value).text =
            simulation.investmentParameter.maturityTotalDays.toString()
        findViewById<TextView>(R.id.tv_monthly_gross_rate_profit_detail_value).text =
            simulation.monthlyGrossRateProfit.convertToPercent()
        findViewById<TextView>(R.id.tv_rate_detail_value).text =
            simulation.investmentParameter.rate.convertToPercent()
        findViewById<TextView>(R.id.tv_yearly_interest_rate_detail_value).text =
            simulation.investmentParameter.yearlyInterestRate.convertToPercent()
        findViewById<TextView>(R.id.tv_annual_gross_rate_profit_detail_value).text =
            simulation.annualGrossRateProfit.convertToPercent()
    }

    private fun getSpannableGrossAmountProfit(grossAmountProfit: String): Spannable {
        val text = resources.getString(R.string.simulation_result_gross_amount_profit)
        val text2: String = (text + grossAmountProfit)
        val spannable: Spannable = SpannableString(text2)

        spannable.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.BLUE_00C8B3)),
            text.length,
            (text + grossAmountProfit).length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannable
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
