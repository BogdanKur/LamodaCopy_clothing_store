package com.example.lamodacopy

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.lamodacopy.MainFragment.Companion.bagsCount
import com.example.lamodacopy.ProfileFragment.Companion.numberOfAuthUser
import com.example.lamodacopy.databinding.FragmentDescribeClothBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DescribeClothFragment : Fragment() {
    private var _binding: FragmentDescribeClothBinding? = null
    val binding get() = _binding!!
    lateinit var firstImg: ClothesCollection
    lateinit var viewModel: DescribeClothFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDescribeClothBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(DescribeClothFragmentViewModel::class.java)
        val navController = findNavController()
        binding.toolbar.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{_, destination, _ ->
            destination.label = ""
            binding.toolbar.title = destination.label
            binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        }
        arguments?.let { bundle ->
            val imageUrl = bundle.getString("IMAGE_URL")
            val description = bundle.getString("DESCRIPTION")
            val cost = bundle.getString("COST")

            Glide.with(this).load(imageUrl).into(binding.imageView)
            binding.textView1.text = description
            binding.textView2.text = cost
            firstImg = ClothesCollection(imageUrl!!, description!!, cost!!)
        }
        binding.btnAddBag.setOnClickListener {
            addToFavorites(firstImg)
            bagsCount = "2"
            viewModel.updateBagCount(numberOfAuthUser, bagsCount) {

            }
        }

        return view
    }

    private fun addToFavorites(item: ClothesCollection) {
        val userId = numberOfAuthUser
        val db = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("favorites")

        val itemId = db.push().key
        db.child(itemId ?: "").setValue(item)
            .addOnSuccessListener {
            }
            .addOnFailureListener { e ->
            }
    }
}