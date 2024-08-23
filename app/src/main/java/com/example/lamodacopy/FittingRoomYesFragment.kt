package com.example.lamodacopy

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lamodacopy.DeliveryFragment.Companion.isClick
import com.example.lamodacopy.databinding.FragmentFittingRoomYesBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment

class FittingRoomYesFragment : Fragment() {
    private var _binding: FragmentFittingRoomYesBinding? = null
    val binding get() = _binding!!

    var isChooseBtnFittingRooms = isClick


    lateinit var viewModel: FittingRoomYesFragmentViewModel

    @SuppressLint("ResourceAsColor", "ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFittingRoomYesBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(FittingRoomYesFragmentViewModel::class.java)
        binding.fittingRoomsViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val adapter = MarkersAdapter()
        binding.markers.adapter = adapter

        adapter.citiesOfRussiaOver50k = viewModel.whoseCountry(DeliveryFragmentViewModel.btnTextDelivery)


        val mapFragment = childFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            adapter.markers = viewModel.markersList(DeliveryFragmentViewModel.btnTextDelivery, googleMap)
            for (marker in adapter.markers) {
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.position, 7f))
                googleMap.setOnMarkerClickListener {
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.position, 18f))
                    true
                }
            }
        }

        val navController = findNavController()
        binding.toolbar.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{_, destination, _ ->
            destination.label = "Доставка и примерка"
            binding.toolbar.title = destination.label
        }

        isChooseBtnFittingRooms = isClick

        if(isChooseBtnFittingRooms == 1) {
            val currentBackgroundColor = (binding.btnFitting.background as ColorDrawable).color

            val newBackgroundColor: Int
            val newTextColor: Int

            if (currentBackgroundColor == ContextCompat.getColor(requireContext(), R.color.black)) {
                newBackgroundColor = ContextCompat.getColor(requireContext(), R.color.white)
                newTextColor = ContextCompat.getColor(requireContext(), R.color.black)
            } else {
                newBackgroundColor = ContextCompat.getColor(requireContext(), R.color.black)
                newTextColor = ContextCompat.getColor(requireContext(), R.color.white)
            }

            binding.btnFitting.setBackgroundColor(newBackgroundColor)
            binding.btnFitting.setTextColor(newTextColor)
        }

        binding.btnFitting.setOnClickListener {
            val currentBackgroundColor = (binding.btnFitting.background as ColorDrawable).color

            val newBackgroundColor: Int
            val newTextColor: Int

            if (currentBackgroundColor == ContextCompat.getColor(requireContext(), R.color.black)) {
                newBackgroundColor = ContextCompat.getColor(requireContext(), R.color.white)
                newTextColor = ContextCompat.getColor(requireContext(), R.color.black)
            } else {
                newBackgroundColor = ContextCompat.getColor(requireContext(), R.color.black)
                newTextColor = ContextCompat.getColor(requireContext(), R.color.white)
            }

            binding.btnFitting.setBackgroundColor(newBackgroundColor)
            binding.btnFitting.setTextColor(newTextColor)
        }
        binding.btnPayByCarta.setOnClickListener {
            val currentBackgroundColor = (binding.btnPayByCarta.background as ColorDrawable).color

            val newBackgroundColor: Int
            val newTextColor: Int

            if (currentBackgroundColor == ContextCompat.getColor(requireContext(), R.color.black)) {
                newBackgroundColor = ContextCompat.getColor(requireContext(), R.color.white)
                newTextColor = ContextCompat.getColor(requireContext(), R.color.black)
            } else {
                newBackgroundColor = ContextCompat.getColor(requireContext(), R.color.black)
                newTextColor = ContextCompat.getColor(requireContext(), R.color.white)
            }

            binding.btnPayByCarta.setBackgroundColor(newBackgroundColor)
            binding.btnPayByCarta.setTextColor(newTextColor)
        }



        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isClick = 0
    }
}