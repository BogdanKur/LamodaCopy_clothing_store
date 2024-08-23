package com.example.lamodacopy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lamodacopy.MainFragment.Companion.electsCount
import com.example.lamodacopy.ProfileFragment.Companion.numberOfAuthUser
import com.example.lamodacopy.databinding.FragmentElectBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ElectFragment : Fragment() {
    private var _binding: FragmentElectBinding? = null
    val binding get() = _binding!!
    lateinit var viewModel: ElectFragmentViewModel
    private var favoritesList: List<ClothesCollection> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentElectBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(ElectFragmentViewModel::class.java)
        viewModel.getBagCount(numberOfAuthUser) { bc ->
            if (bc != null) {
                electsCount = bc
            }
        }
        val navController = findNavController()
        navController.addOnDestinationChangedListener{_, destination, _ ->
            destination.label = "Избранное"
            binding.toolbar.title = destination.label
        }
        if(electsCount == "1" || electsCount == "0") {
            binding.linearLayoutIfNoBuys.visibility = View.VISIBLE
        } else {
            binding.linearLayoutIfNoBuys.visibility = View.GONE
            getElects(object : FavoritesCallback {
                override fun onFavoritesReceived(favorites: List<ClothesCollection>) {
                    if(favorites.isNotEmpty()) {
                        val uniqueFavorites = favorites.distinctBy { it.url }
                        favoritesList = uniqueFavorites
                        val adapter = ClothesCollectionElectAdapter(navController, favoritesList)
                        binding.recyclerViewElect.adapter = adapter
                    }
                }
            })
        }
        return view
    }


    private fun getElects(callback: FavoritesCallback) {
        val userId = numberOfAuthUser
        val db = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("elects")

        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val favoritesList = mutableListOf<ClothesCollection>()
                    for (favoriteSnap in snapshot.children) {
                        val item = favoriteSnap.getValue(ClothesCollection::class.java)
                        if (item != null) {
                            favoritesList.add(item)
                        }
                    }
                    callback.onFavoritesReceived(favoritesList)
                } else {
                    callback.onFavoritesReceived(emptyList())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                callback.onFavoritesReceived(emptyList())
            }
        })
    }
}