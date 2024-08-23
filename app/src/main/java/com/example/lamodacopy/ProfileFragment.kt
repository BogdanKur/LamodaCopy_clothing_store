package com.example.lamodacopy

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lamodacopy.EnterFragment.SharedPreferencesHelper
import com.example.lamodacopy.databinding.FragmentProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ProfileFragment : Fragment() {

    private val usersRef = RegistrationFragment.users
    private var _binding: FragmentProfileBinding? = null
    val binding get() = _binding!!
    lateinit var viewModel: ProfileFragmentViewModel
    var country = ""

    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    companion object {
        var uses: String = "0"
        var numberOfAuthUser : String = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(ProfileFragmentViewModel::class.java)

        val radioGroup = activity?.findViewById<RadioGroup>(R.id.countryGroup)
        val coutryButton1 = activity?.findViewById<RadioButton>(R.id.coutryButton1)

        val countryType = radioGroup?.checkedRadioButtonId
        if(countryType == -1) {
            coutryButton1?.isChecked = true
        }

        val radioButtonId = context?.let {
            SharedPreferencesHelper.getSelectedRadioButton(it)
        }

        radioGroup?.check(radioButtonId!!)

        usersRef.child(numberOfAuthUser).child("country").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                country = snapshot.getValue(String::class.java).toString()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error getting user name: ${error.message}")
            }
        })

        if(country == "Россия") {
            val drawable = resources.getDrawable(R.drawable.russia1)
            binding.btnCountry1.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
        }

        if(country == "Белоруссия") {
            val drawable = resources.getDrawable(R.drawable.belorussia1)
            binding.btnCountry1.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
        }

        if(country == "Казахстан") {
            val drawable = resources.getDrawable(R.drawable.kazachstan1)
            binding.btnCountry1.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
        }

        binding.profileFragmentViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        fun startUpdating() {
            runnable = object : Runnable {
                override fun run() {
                    viewModel.getFirst(numberOfAuthUser) { firstLetter ->
                        binding.textView.text = firstLetter
                    }

                    viewModel.getName(numberOfAuthUser) { name ->
                        binding.textView2.text = name
                    }

                    handler.postDelayed(this, 1000)
                }
            }
            handler.post(runnable)
        }
        fun stopUpdating() {
            handler.removeCallbacks(runnable)
        }


        startUpdating()

        binding.btnProfileFromAvatar.setOnClickListener {
            stopUpdating()
        }

        binding.btnNots.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_NotFragment)
        }

        binding.btnYourBuys.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_ordersFragment)
        }

        binding.btnSertificat1.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_sertificateFragment)
        }

        binding.btnCountry1.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_countryFragment)
        }

        binding.btnGift1.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_managingSubscriptionsFragment)
        }

        binding.btnHelpCenter1.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_supportCenterFragment)
        }



        return view
    }

    override fun onResume() {
        super.onResume()
        usersRef.child(numberOfAuthUser).child("country").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                country = snapshot.getValue(String::class.java).toString()
                Log.e("country", country)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error getting user name: ${error.message}")
            }
        })
        if(country == "Россия") {
            val drawable = resources.getDrawable(R.drawable.russia1)
            binding.btnCountry1.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
            Log.e("countryR", country)
        }

        if(country == "Белоруссия") {
            val drawable = resources.getDrawable(R.drawable.belorussia1)
            binding.btnCountry1.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
            Log.e("countryB", country)
        }

        if(country == "Казахстан") {
            val drawable = resources.getDrawable(R.drawable.kazachstan1)
            binding.btnCountry1.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
            Log.e("countryK", country)
        }
    }

}