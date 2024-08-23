package com.example.lamodacopy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lamodacopy.databinding.FragmentNotBinding

class NotFragment : Fragment() {
    private var _binding: FragmentNotBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotBinding.inflate(inflater, container, false)
        val view = binding.root
        setHasOptionsMenu(true)

        val navController = findNavController()
        if (navController != null && _binding != null) {
            binding.toolbarNotification.setupWithNavController(navController)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.label != null) {
                    destination.label = "Сообщения"
                    binding.toolbarNotification.title = destination.label
                }
            }
        }
        return view
    }
}