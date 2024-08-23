package com.example.lamodacopy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lamodacopy.databinding.FragmentDeliveryBinding

class DeliveryFragment : Fragment() {

    companion object{
        var isClick: Int = 0
    }

    private var _binding: FragmentDeliveryBinding? = null
    val binding get() = _binding!!

    lateinit var viewModel: DeliveryFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeliveryBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(DeliveryFragmentViewModel::class.java)


        binding.deliveryViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        binding.toolbar.setupWithNavController(findNavController())
        val navController = findNavController()
        navController.addOnDestinationChangedListener{_, destination, _ ->
            destination.label = "Доставка и примерка"
            binding.toolbar.title = destination.label
        }

        binding.btnPickup.setOnClickListener {
            isClick = 1
            navController.navigate(R.id.action_deliveryFragment_to_pickupFragment)
        }

        binding.btnCountryChoose.setOnClickListener {
            navController.navigate(R.id.action_deliveryFragment_to_whereAreYouFragment)
            isClick = 1
        }

        if(DeliveryFragmentViewModel.btnTextDelivery != "") {
            binding.btnCountryChoose.text = DeliveryFragmentViewModel.btnTextDelivery
            binding.btnPickup.visibility = View.VISIBLE
            binding.btnCourierDelivery.visibility = View.VISIBLE
        } else {
            binding.btnCountryChoose.text = "Город не выбран"
            binding.btnPickup.visibility = View.GONE
            binding.btnCourierDelivery.visibility = View.GONE
        }

        binding.btnCourierDelivery.setOnClickListener {
            navController.navigate(R.id.action_deliveryFragment_to_coruierDeliveryDragment)
        }

        return view
    }
}