package com.example.lamodacopy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lamodacopy.databinding.FragmentManagingSubscriptionsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class managingSubscriptionsFragment : Fragment() {
    private var _binding: FragmentManagingSubscriptionsBinding? = null
    val binding get() = _binding!!

    companion object {
        var isClick: Int = 0
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentManagingSubscriptionsBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.toolbarManagingSertificate.setupWithNavController(findNavController())
        val navController = findNavController()

        navController.addOnDestinationChangedListener{_, destination, _ ->
            destination.label = "Управление подписками"
            binding.toolbarManagingSertificate.title = destination.label
        }

        binding.btnSubscribe.setOnClickListener {
            navController.navigate(R.id.action_managingSubscriptionsFragment_to_saleAndPromoFragment)
            isClick = 1
        }


        return  view
    }

    override fun onDestroyView() {
        super.onDestroyView()

        if(isClick == 0) {
            val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNavigationView?.visibility = View.VISIBLE
        }

    }
}