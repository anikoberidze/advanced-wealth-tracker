package com.ani.wealthtracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.ani.wealthtracker.databinding.FragmentAnalyticsBinding
import com.ani.wealthtracker.model.WealthManager
import java.text.DecimalFormat

class AnalyticsFragment : Fragment() {

    private var _binding: FragmentAnalyticsBinding? = null
    private val binding get() = _binding!!

    private val df = DecimalFormat("#,##0.00")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnalyticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener(InputFragment.REQUEST_KEY) { _, bundle ->
            val income = bundle.getDouble(InputFragment.KEY_INCOME)
            val expenses = bundle.getDouble(InputFragment.KEY_EXPENSES)
            updateUI(income, expenses)
        }

        binding.akZeTvNoData.visibility = View.VISIBLE
        binding.akZeAnalyticsContent.visibility = View.GONE
    }

    private fun updateUI(income: Double, expenses: Double) {
        val savings = WealthManager.calculateSavings(income, expenses)
        val net = WealthManager.calculateNet(income, expenses)
        val savingsRate = WealthManager.savingsRate(income, expenses)
        val expenseRatio = WealthManager.expenseRatio(income, expenses)

        binding.akZeTvNoData.visibility = View.GONE
        binding.akZeAnalyticsContent.visibility = View.VISIBLE

        binding.akZeTvIncomeVal.text = "₾ ${df.format(income)}"
        binding.akZeTvExpensesVal.text = "₾ ${df.format(expenses)}"
        binding.akZeTvNetVal.text = "₾ ${df.format(net)}"
        binding.akZeTvKVal.text = String.format("%.4f", WealthManager.K)
        binding.akZeTvSavingsVal.text = "₾ ${df.format(savings)}"
        binding.akZeTvSavingsRate.text = String.format("%.1f%%", savingsRate)
        binding.akZeTvExpenseRatio.text = String.format("%.1f%%", expenseRatio)


        val context = requireContext()
        when {
            savings > 0 -> {
                binding.akZeTvSavingsVal.setTextColor(context.getColor(android.R.color.holo_green_dark))
                binding.akZeTvSavingsLabel.text = "Final Savings (K formula)"
            }
            savings == 0.0 -> {
                binding.akZeTvSavingsVal.setTextColor(context.getColor(android.R.color.holo_orange_dark))
                binding.akZeTvSavingsLabel.text = "Final Savings (K formula)"
            }
            else -> {
                binding.akZeTvSavingsVal.setTextColor(context.getColor(android.R.color.holo_red_dark))
                binding.akZeTvSavingsLabel.text = "Final Savings (K formula)"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}