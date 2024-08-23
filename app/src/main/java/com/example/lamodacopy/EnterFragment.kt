package com.example.lamodacopy

import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lamodacopy.ProfileFragment.Companion.numberOfAuthUser
import com.example.lamodacopy.RegistrationFragment.Companion.auth
import com.example.lamodacopy.databinding.FragmentEnterBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class EnterFragment : Fragment()  {

    private var _binding: FragmentEnterBinding? = null
    val binding get() = _binding!!
    private lateinit var verificationId: String

    companion object {
        var enterCode = ""
        var verId = ""
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnterBinding.inflate(inflater, container, false)
        val view = binding.root


        val spannableString = SpannableString("Войти по коду или через соцсети")
        val clickableSpan1 = object : ClickableSpan() {
            override fun onClick(textView: View) {
                binding.linearLayout1.visibility = View.GONE
                binding.linearLayout2.visibility = View.VISIBLE
            }
        }
        val clickableSpan2 = object : ClickableSpan() {
            override fun onClick(textView: View) {
                // Действие при нажатии на вторую кнопку
            }
        }
        spannableString.setSpan(clickableSpan1, 9, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(clickableSpan2, 24, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvCode.text = spannableString
        binding.tvCode.movementMethod = LinkMovementMethod.getInstance()

        val spannableStringPass = SpannableString("Войти по паролю или через соцсети")
        val clickableSpanPass1 = object : ClickableSpan() {
            override fun onClick(textView: View) {
                binding.linearLayout1.visibility = View.VISIBLE
                binding.linearLayout2.visibility = View.GONE
            }
        }
        val clickableSpanPass2 = object : ClickableSpan() {
            override fun onClick(textView: View) {
                // Действие при нажатии на вторую кнопку
            }
        }
        spannableStringPass.setSpan(clickableSpanPass1, 9, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableStringPass.setSpan(clickableSpanPass2, 26, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvPassword.text = spannableStringPass
        binding.tvPassword.movementMethod = LinkMovementMethod.getInstance()

        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.notFragment -> {
                    findNavController().navigate(R.id.notFragment)
                }
            }
            true
        }

        binding.button2.setOnClickListener {
            findNavController().navigate(R.id.action_enterFragment_to_registrationFragment)
        }

        binding.btnAuth.setOnClickListener {
            sendVerificationCode()
            val bottomSheetFragment = BottomShitEnterFragment()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)

        }

        binding.btnSertificat1.setOnClickListener {
            findNavController().navigate(R.id.action_enterFragment_to_sertificateFragment)
            val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNavigationView?.visibility = View.GONE
        }

        binding.btnSertificat2.setOnClickListener {
            findNavController().navigate(R.id.action_enterFragment_to_sertificateFragment)
            val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNavigationView?.visibility = View.GONE
        }

        binding.btnGift1.setOnClickListener {
            findNavController().navigate(R.id.action_enterFragment_to_managingSubscriptionsFragment)
            val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNavigationView?.visibility = View.GONE
        }

        binding.btnGift2.setOnClickListener {
            findNavController().navigate(R.id.action_enterFragment_to_managingSubscriptionsFragment)
            val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNavigationView?.visibility = View.GONE
        }

        binding.btnCountry1.setOnClickListener {
            findNavController().navigate(R.id.action_enterFragment_to_countryFragment)
            val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNavigationView?.visibility = View.GONE
        }

        binding.btnCountry2.setOnClickListener {
            findNavController().navigate(R.id.action_enterFragment_to_countryFragment)
            val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNavigationView?.visibility = View.GONE
        }

        binding.btnDelivery1.setOnClickListener {
            findNavController().navigate(R.id.action_enterFragment_to_deliveryFragment)
        }

        binding.btnDelivery2.setOnClickListener {
            findNavController().navigate(R.id.action_enterFragment_to_deliveryFragment)
        }

        binding.btnHelpCenter1.setOnClickListener {
            findNavController().navigate(R.id.action_enterFragment_to_supportCenterFragment)
        }

        binding.btnHelpCenter2.setOnClickListener {
            findNavController().navigate(R.id.action_enterFragment_to_supportCenterFragment)
        }


        val radioGroup = activity?.findViewById<RadioGroup>(R.id.countryGroup)
        val coutryButton1 = activity?.findViewById<RadioButton>(R.id.coutryButton1)
        val coutryButton2 = activity?.findViewById<RadioButton>(R.id.coutryButton2)
        val coutryButton3 = activity?.findViewById<RadioButton>(R.id.coutryButton3)

        val countryType = radioGroup?.checkedRadioButtonId
        if(countryType == -1) {
            coutryButton1?.isChecked = true
        }

        val radioButtonId = context?.let {
            SharedPreferencesHelper.getSelectedRadioButton(it)
        }

        radioGroup?.check(radioButtonId!!)


        if(coutryButton1?.isChecked == true) {
            val drawable = resources.getDrawable(R.drawable.russia1)
            binding.btnCountry1.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
            binding.btnCountry2.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
        }

        if(coutryButton2?.isChecked == true) {
            val drawable = resources.getDrawable(R.drawable.belorussia1)
            binding.btnCountry1.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
            binding.btnCountry2.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
        }

        if(coutryButton3?.isChecked == true) {
            val drawable = resources.getDrawable(R.drawable.kazachstan1)
            binding.btnCountry1.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
            binding.btnCountry2.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
        }






        return view
    }


    private fun sendVerificationCode() {
        if(!binding.etAuth.text.toString().contains("@", ignoreCase = true)) {

            var phoneNumber = binding.etAuth.text.toString().trim()
            if (phoneNumber.first() == '8') {
                phoneNumber = phoneNumber.replaceFirst("8", "+7")
            }

            val usersRef = RegistrationFragment.users

            usersRef.orderByChild("phoneNumber").equalTo(phoneNumber)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (userSnapshot in snapshot.children) {
                            val userId = userSnapshot.key
                            numberOfAuthUser = userId!!
                            Log.e("numbv", numberOfAuthUser)
                            Log.e("User ID:", userId!!)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("Error getting user ID: ", error.message)
                    }
                })



            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(requireActivity())
                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                        signInWithPhoneAuthCredential(credential)
                    }

                    override fun onVerificationFailed(e: FirebaseException) {
                        Toast.makeText(context, "Ошибка: неверный номер телефона", Toast.LENGTH_LONG).show()
                        Log.e("Error404", e.message!!)
                    }

                    override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                        this@EnterFragment.verificationId = verificationId
                        verId = verificationId
                        Toast.makeText(context, "Код отправлен", Toast.LENGTH_SHORT).show()
                    }
                })
                .build()

            PhoneAuthProvider.verifyPhoneNumber(options)
        }

    }


    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Успешно авторизован", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Ошибка авторизации", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    object SharedPreferencesHelper {
        private const val PREFS_NAME = "MyPrefs"
        private const val SELECTED_RADIO_BUTTON = "SelectedRadioButton"

        fun saveSelectedRadioButton(context: Context, radioButtonId: Int) {
            val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            with (sharedPref.edit()) {
                putInt(SELECTED_RADIO_BUTTON, radioButtonId)
                apply()
            }
        }

        fun getSelectedRadioButton(context: Context): Int {
            val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            return sharedPref.getInt(SELECTED_RADIO_BUTTON, 0) // default value is 0
        }
    }
}