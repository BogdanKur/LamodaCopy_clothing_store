package com.example.lamodacopy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lamodacopy.databinding.FragmentCoruierDeliveryBinding


class CoruierDeliveryFragment : Fragment() {

    private var _binding: FragmentCoruierDeliveryBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoruierDeliveryBinding.inflate(inflater, container, false)
        val view = binding.root
        val navController = findNavController()
        binding.toolbar.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{_, destination, _ ->
            destination.label = "Доставка и примерка"
            binding.toolbar.title = destination.label
        }

        binding.btnThanks.setOnClickListener {
            navController.navigate(R.id.action_courierDeliveryFragment_to_deliveryFragment)
        }

        return view
    }

}