package com.example.lamodacopy

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lamodacopy.ProfileFragment.Companion.numberOfAuthUser
import com.example.lamodacopy.databinding.FragmentMainBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore


class MainFragment : Fragment(), ClothesCollectionAdapter.ItemClickListener {
    private lateinit var firestore: FirebaseFirestore
    private var _binding: FragmentMainBinding? = null
    val binding get() = _binding!!
    var gender: String = "man"
    var category: String = "все"

    companion object{
        var bagsCount: String = "0"
        var electsCount: String = "0"
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        firestore = FirebaseFirestore.getInstance()
        loadImages()

        binding.btnWomen.setOnClickListener {
            binding.btnWomen.setBackgroundResource(R.drawable.btn_white_shape)
            binding.btnMan.setBackgroundResource(Color.TRANSPARENT)
            binding.btnKids.setBackgroundResource(Color.TRANSPARENT)
            gender = "woman"
            loadImages()
        }
        binding.btnMan.setOnClickListener {
            binding.btnWomen.setBackgroundResource(Color.TRANSPARENT)
            binding.btnMan.setBackgroundResource(R.drawable.btn_white_shape)
            binding.btnKids.setBackgroundResource(Color.TRANSPARENT)
            gender = "man"
            loadImages()
        }
        binding.btnKids.setOnClickListener {
            binding.btnWomen.setBackgroundResource(Color.TRANSPARENT)
            binding.btnMan.setBackgroundResource(Color.TRANSPARENT)
            binding.btnKids.setBackgroundResource(R.drawable.btn_white_shape)
            gender = "kids"
            loadImages()
        }

        binding.btnAll.setOnClickListener {
            binding.btnAll.setBackgroundResource(R.drawable.btn_categories_main)
            binding.btnPremium.setBackgroundResource(Color.TRANSPARENT)
            binding.btnSport.setBackgroundResource(Color.TRANSPARENT)
            binding.btnBeuty.setBackgroundResource(Color.TRANSPARENT)
            binding.btnHouse.setBackgroundResource(Color.TRANSPARENT)
            binding.btnIdea.setBackgroundResource(Color.TRANSPARENT)
            category = "все"
            loadImages()
        }
        binding.btnPremium.setOnClickListener {
            binding.btnAll.setBackgroundResource(Color.TRANSPARENT)
            binding.btnPremium.setBackgroundResource(R.drawable.btn_categories_main)
            binding.btnSport.setBackgroundResource(Color.TRANSPARENT)
            binding.btnBeuty.setBackgroundResource(Color.TRANSPARENT)
            binding.btnHouse.setBackgroundResource(Color.TRANSPARENT)
            binding.btnIdea.setBackgroundResource(Color.TRANSPARENT)
            category = "премиум"
            loadImages()
        }
        binding.btnSport.setOnClickListener {
            binding.btnAll.setBackgroundResource(Color.TRANSPARENT)
            binding.btnPremium.setBackgroundResource(Color.TRANSPARENT)
            binding.btnSport.setBackgroundResource(R.drawable.btn_categories_main)
            binding.btnBeuty.setBackgroundResource(Color.TRANSPARENT)
            binding.btnHouse.setBackgroundResource(Color.TRANSPARENT)
            binding.btnIdea.setBackgroundResource(Color.TRANSPARENT)
            category = "спорт"
            loadImages()
        }
        binding.btnBeuty.setOnClickListener {
            binding.btnAll.setBackgroundResource(Color.TRANSPARENT)
            binding.btnPremium.setBackgroundResource(Color.TRANSPARENT)
            binding.btnSport.setBackgroundResource(Color.TRANSPARENT)
            binding.btnBeuty.setBackgroundResource(R.drawable.btn_categories_main)
            binding.btnHouse.setBackgroundResource(Color.TRANSPARENT)
            binding.btnIdea.setBackgroundResource(Color.TRANSPARENT)
            category = "красота"
            loadImages()
        }
        binding.btnHouse.setOnClickListener {
            binding.btnAll.setBackgroundResource(Color.TRANSPARENT)
            binding.btnPremium.setBackgroundResource(Color.TRANSPARENT)
            binding.btnSport.setBackgroundResource(Color.TRANSPARENT)
            binding.btnBeuty.setBackgroundResource(Color.TRANSPARENT)
            binding.btnHouse.setBackgroundResource(R.drawable.btn_categories_main)
            binding.btnIdea.setBackgroundResource(Color.TRANSPARENT)
            category = "дом"
            loadImages()
        }
        binding.btnIdea.setOnClickListener {
            binding.btnAll.setBackgroundResource(Color.TRANSPARENT)
            binding.btnPremium.setBackgroundResource(Color.TRANSPARENT)
            binding.btnSport.setBackgroundResource(Color.TRANSPARENT)
            binding.btnBeuty.setBackgroundResource(Color.TRANSPARENT)
            binding.btnHouse.setBackgroundResource(Color.TRANSPARENT)
            binding.btnIdea.setBackgroundResource(R.drawable.btn_categories_main)
            category = "идеи"
            loadImages()
        }

        return view
    }

    private fun loadImages() {
        val navController = findNavController()
        firestore.collection("images")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val imageList = mutableListOf<ClothesCollection>()
                val adapter = ClothesCollectionAdapter(navController, imageList, this)
                for (document in querySnapshot) {
                    val imageData = document.toObject(ClothesCollection::class.java)
                    if(imageData.gender == gender && (imageData.category == category || category == "все")) {
                        if (binding.etCatalogSearch.text.isNullOrEmpty()) {
                            imageList.add(imageData)
                        }
                        binding.etCatalogSearch.addTextChangedListener(object : TextWatcher {
                            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                                adapter.filter(s.toString())
                            }
                            override fun afterTextChanged(s: Editable?) {}
                        })
                        binding.tasksList.adapter = adapter
                    }
                }
                binding.tasksList.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    override fun addToElect(item: ClothesCollection) {
        val userId = numberOfAuthUser
        val db = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("elects")
        electsCount = "2"
        val itemId = db.push().key
        db.child(itemId ?: "").setValue(item)
            .addOnSuccessListener {
            }
            .addOnFailureListener { e ->
            }
    }
    override fun onResume() {
        super.onResume()
        gender = "man"
        category = "все"
    }
}