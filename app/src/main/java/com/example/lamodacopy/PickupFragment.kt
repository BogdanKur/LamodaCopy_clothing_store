package com.example.lamodacopy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lamodacopy.DeliveryFragment.Companion.isClick
import com.example.lamodacopy.databinding.FragmentEnterBinding
import com.example.lamodacopy.databinding.FragmentPickupBinding

class PickupFragment : Fragment() {

    private var _binding: FragmentPickupBinding? = null
    val binding get() = _binding!!
    lateinit var viewModel: PickupFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPickupBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(PickupFragmentViewModel::class.java)

        binding.pickupFragmentViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.toolbar.setupWithNavController(findNavController())
        val navController = findNavController()
        navController.addOnDestinationChangedListener{_, destination, _ ->
            destination.label = "Доставка и примерка"
            binding.toolbar.title = destination.label
        }

        binding.btnPickup.setOnClickListener {
            navController.navigate(R.id.action_pickupFragment_to_fittingRoomYes)
            isClick = 1
        }

        binding.btnCourierDelivery.setOnClickListener {
            navController.navigate(R.id.action_pickupFragment_to_fittingRoomYes)
        }

        return view
    }
}