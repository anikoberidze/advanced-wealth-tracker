package com.ani.wealthtracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.ani.wealthtracker.databinding.FragmentInputBinding
import com.ani.wealthtracker.model.WealthManager

class InputFragment : Fragment() {

    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.akZeBtnSave.setOnClickListener {
            val incomeStr = binding.akZeEtIncome.text.toString()
            val expenseStr = binding.akZeEtExpenses.text.toString()

            var valid = true

            if (!WealthManager.isValidInput(incomeStr)) {
                binding.akZeEtIncome.error = "Please enter a valid income amount"
                valid = false
            } else {
                binding.akZeEtIncome.error = null
            }

            if (!WealthManager.isValidInput(expenseStr)) {
                binding.akZeEtExpenses.error = "Please enter a valid expense amount"
                valid = false
            } else {
                binding.akZeEtExpenses.error = null
            }

            if (valid) {
                val income = incomeStr.toDouble()
                val expenses = expenseStr.toDouble()

                if (expenses > income) {
                    binding.akZeEtExpenses.error = "Expenses cannot exceed income"
                    return@setOnClickListener
                }


                setFragmentResult(
                    REQUEST_KEY,
                    bundleOf(
                        KEY_INCOME to income,
                        KEY_EXPENSES to expenses
                    )
                )

                binding.akZeTvStatus.visibility = View.VISIBLE
                binding.akZeTvStatus.text = "✅ Data saved! Swipe up to see analytics."
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val REQUEST_KEY = "wealth_data_request"
        const val KEY_INCOME = "income"
        const val KEY_EXPENSES = "expenses"
    }
}