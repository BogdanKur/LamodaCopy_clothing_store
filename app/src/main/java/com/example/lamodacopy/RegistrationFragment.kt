package com.example.lamodacopy

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lamodacopy.ProfileFragment.Companion.numberOfAuthUser
import com.example.lamodacopy.databinding.FragmentRegistrationBinding
import com.google.android.gms.auth.api.Auth
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    val binding get() = _binding!!
    lateinit var viewModel: RegistrationFragmentViewModel

    companion object{
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        val db: FirebaseDatabase = FirebaseDatabase.getInstance()
        val users: DatabaseReference = db.getReference("Users")
        val userId = FirebaseAuth.getInstance().getCurrentUser()?.getUid()
    }

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(RegistrationFragmentViewModel::class.java)
        val navController = findNavController()
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.notFragment -> {
                    findNavController().navigate(R.id.notFragment)
                }
            }
            true
        }

        binding.button.setOnClickListener {
            navController.navigate(R.id.action_registrationFragment_to_enterFragment)
        }
        //val retrofit = Retrofit.Builder()
        //    .baseUrl("http://192.168.0.109:51028/host/")
        //    .addConverterFactory(GsonConverterFactory.create())
        //    .build()

       // val apiService = retrofit.create(MainAPI::class.java)
        binding.btnReg.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                var phoneNumber = binding.etNumber.text.toString()
                if (phoneNumber.first() == '8') {
                    phoneNumber = phoneNumber.replaceFirst("8", "+7")
                }


                val user1 = User(binding.etName.text.toString(), phoneNumber, binding.etEmail.text.toString())
                viewModel.addNewUser(user1.name!!, user1.email!!, user1.tel!!)
                Log.e("numbv", numberOfAuthUser)
                //apiService.saveUser(user1)
            }

            val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)!!
            MainActivity.BottomNavigationUtils.replaceMenuItem(bottomNavigationView, requireContext())

            navController.navigate(R.id.action_registrationFragment_to_profileFragment)
        }

        return view
    }
}