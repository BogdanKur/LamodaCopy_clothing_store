package com.example.lamodacopy

import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lamodacopy.databinding.FragmentManagingSubscriptionsBinding
import com.example.lamodacopy.databinding.FragmentSaleAndPromoBinding
import com.example.lamodacopy.managingSubscriptionsFragment.Companion.isClick
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.provider.Settings
import android.widget.Switch
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class SaleAndPromoFragment : Fragment() {

    private var _binding: FragmentSaleAndPromoBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSaleAndPromoBinding.inflate(inflater, container, false)
        val view = binding.root

        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView?.visibility = View.GONE

        binding.toolbar.setupWithNavController(findNavController())
        val navController = findNavController()
        navController.addOnDestinationChangedListener{_, destination, _ ->
            destination.label = "Распродажи и акции"
            binding.toolbar.title = destination.label
        }

        binding.swButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                openNotificationSettings()
            }
        }

        binding.btnSubscribe.setOnClickListener {
            navController.navigate(R.id.action_saleAndPromoFragment_to_managingSubscriptionsFragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isClick = 0
    }


    private fun openNotificationSettings() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        intent.data = Uri.fromParts("package", "com.example.lamodacopy", null)
        startActivity(intent)
    }


}