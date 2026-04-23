package com.ani.wealthtracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ani.wealthtracker.databinding.FragmentProfileBinding
import com.ani.wealthtracker.model.WealthManager

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.akZeTvName.text = "Ani Koberidze"
        binding.akZeTvDob.text = "19 August 2004"
        binding.akZeTvAge.text = "21 years old"
        binding.akZeTvInitials.text = "AK"
        binding.akZeTvIdPrefix.text = "ak_ze_"

        val kFormatted = String.format("%.4f", WealthManager.K)
        binding.akZeTvKFormula.text =
            "K = (name letters + surname letters) / day\n= (3 + 9) / 19 = $kFormatted"

        binding.akZeTvOrientation.text =
            "Surname starts with 'K' (consonant)\n→ ViewPager2 Orientation: VERTICAL"

        binding.akZeTvFormula.text =
            "Final Savings = (Income − Expenses) × K\n= (Income − Expenses) × $kFormatted"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}