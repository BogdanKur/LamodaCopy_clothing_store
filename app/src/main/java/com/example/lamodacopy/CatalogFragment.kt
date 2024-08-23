package com.example.lamodacopy

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
import com.example.lamodacopy.databinding.FragmentCatalogBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class CatalogFragment : Fragment(), ClothesCollectionCatalogAdapter.ItemClickListener {
    private lateinit var firestore: FirebaseFirestore
    private var _binding: FragmentCatalogBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        val view = binding.root
        firestore = FirebaseFirestore.getInstance()
        loadImages()


        return view
    }
    private fun loadImages() {
        val navController = findNavController()
        firestore.collection("images")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val imageList = mutableListOf<ClothesCollection>()
                val adapter = ClothesCollectionCatalogAdapter(navController, imageList, this)
                for (document in querySnapshot) {
                    val imageData = document.toObject(ClothesCollection::class.java)
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
                    binding.recyclerViewCatalog.adapter = adapter
                }
                binding.recyclerViewCatalog.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents: ", exception)
            }
    }

    override fun addToElect(item: ClothesCollection) {
        val userId = numberOfAuthUser
        val db = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("elects")

        val itemId = db.push().key
        db.child(itemId ?: "").setValue(item)
            .addOnSuccessListener {
            }
            .addOnFailureListener { e ->
            }
    }
}