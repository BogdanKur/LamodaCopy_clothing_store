package com.example.lamodacopy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lamodacopy.databinding.FragmentSupportCenterBinding

class SupportCenterFragment : Fragment() {
    private var _binding: FragmentSupportCenterBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSupportCenterBinding.inflate(inflater, container, false)
        val view = binding.root
        val navController = findNavController()
        binding.toolbar.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{_, destination, _ ->
            destination.label = "Центр поддержки"
            binding.toolbar.title = destination.label
        }

        binding.btnAskUsQuestion.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }

        return view
    }
}