package com.example.lamodacopy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lamodacopy.databinding.FragmentGiftSertificateBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class GiftSertificateFragment : Fragment() {
    private var _binding: FragmentGiftSertificateBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGiftSertificateBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.toolbarSertificate.setupWithNavController(findNavController())

        val navController = findNavController()
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            destination.label = "Подарочные сертификаты"
            binding.toolbarSertificate.title = destination.label

        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView?.visibility = View.VISIBLE
    }
}