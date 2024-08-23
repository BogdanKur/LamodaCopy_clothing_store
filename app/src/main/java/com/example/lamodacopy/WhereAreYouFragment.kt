package com.example.lamodacopy

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.lamodacopy.DeliveryFragment.Companion.isClick
import com.example.lamodacopy.databinding.FragmentWhereAreYouBinding

class WhereAreYouFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentWhereAreYouBinding? = null
    val binding get() = _binding!!
    lateinit var viewModel: WhereAreYouFragmentViewModel

    companion object{
        lateinit var editText: EditText
    }

    override fun onItemClicks() {
        findNavController().navigate(R.id.action_whereAreYouFragment_to_deliveryFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWhereAreYouBinding.inflate(inflater, container, false)
        val view = binding.root

        editText = view.findViewById(R.id.etCountry)

        viewModel = ViewModelProvider(this).get(WhereAreYouFragmentViewModel::class.java)

        binding.whereAreYou = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = CountryAdapter(this)
        binding.countryList.adapter = adapter

        viewModel.citiesOfRussiaOver50k.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.citiesOfRussiaOver50k = it
            }
        })

        binding.btnKrestik.setOnClickListener{
            findNavController().navigate(R.id.action_whereAreYouFragment_to_deliveryFragment)
        }

        binding.countryList.setOnClickListener {
            findNavController().navigate(R.id.action_whereAreYouFragment_to_deliveryFragment)
        }


        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })


        return view
    }

    fun onItemClick() {
        findNavController().navigate(R.id.action_whereAreYouFragment_to_deliveryFragment)
    }


}