package com.example.lamodacopy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lamodacopy.EnterFragment.Companion.enterCode
import com.example.lamodacopy.EnterFragment.Companion.verId
import com.example.lamodacopy.RegistrationFragment.Companion.auth
import com.example.lamodacopy.databinding.FragmentBottomShitEnterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class BottomShitEnterFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomShitEnterBinding? = null
    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomShitEnterBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    fun verifyCode() {
        val code = enterCode.trim()
        val credential = PhoneAuthProvider.getCredential(verId, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnEtCodeEnter.setOnClickListener {
            enterCode = binding.etCode.text.toString()
            if(enterCode != "") {
                verifyCode()
                findNavController().navigate(R.id.action_enterFragment_to_profileFragment)
                dismiss()
            }
        }
    }
}