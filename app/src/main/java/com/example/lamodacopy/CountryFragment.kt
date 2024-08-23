package com.example.lamodacopy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lamodacopy.ProfileFragment.Companion.numberOfAuthUser
import com.example.lamodacopy.databinding.FragmentCountryBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.FirebaseDatabase

class CountryFragment : Fragment() {
    private var _binding: FragmentCountryBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountryBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.toolbar.setupWithNavController(findNavController())

        val navController = findNavController()
        navController.addOnDestinationChangedListener{_, destination, _ ->
            destination.label = "Страна"
            binding.toolbar.title = destination.label
        }

        val countryType = binding.countryGroup.checkedRadioButtonId
        if(countryType == -1) {
            binding.coutryButton1.isChecked = true
        }

        binding.coutryButton1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                context?.let { EnterFragment.SharedPreferencesHelper.saveSelectedRadioButton(it, binding.coutryButton1.id) }
                binding.coutryButton2.isChecked = false
                binding.coutryButton3.isChecked = false
                addCountry("Россия")
            }
        }

        binding.coutryButton2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                context?.let { EnterFragment.SharedPreferencesHelper.saveSelectedRadioButton(it, binding.coutryButton2.id) }
                binding.coutryButton1.isChecked = false
                binding.coutryButton3.isChecked = false
                addCountry("Белоруссия")
            }
        }

        binding.coutryButton3.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                context?.let { EnterFragment.SharedPreferencesHelper.saveSelectedRadioButton(it, binding.coutryButton3.id) }
                binding.coutryButton1.isChecked = false
                binding.coutryButton2.isChecked = false
                addCountry("Казахстан")
            }
        }

        val radioButtonId = context?.let {
            EnterFragment.SharedPreferencesHelper.getSelectedRadioButton(it)
        }
        binding.countryGroup.check(radioButtonId!!)

        return view
    }

    fun addCountry(item: String) {
        val userId = numberOfAuthUser
        val db = FirebaseDatabase.getInstance().getReference("Users").child(userId)
        db.child("country").setValue(item)
            .addOnSuccessListener {
            }
            .addOnFailureListener { e ->
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView?.visibility = View.VISIBLE

    }
}