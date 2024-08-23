package com.example.lamodacopy

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lamodacopy.MainFragment.Companion.bagsCount
import com.example.lamodacopy.ProfileFragment.Companion.numberOfAuthUser
import com.example.lamodacopy.databinding.FragmentBagBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class BagFragment : Fragment() {
    private var _binding: FragmentBagBinding? = null
    val binding get() = _binding!!
    lateinit var viewModel: BagFragmentViewModel
    private var favoritesList: List<ClothesCollection> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBagBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(BagFragmentViewModel::class.java)
        viewModel.getBagCount(numberOfAuthUser) { bc ->
            if (bc != null) {
                bagsCount = bc
            }
        }
        val navController = findNavController()
        Log.e("f11", bagsCount)

        if(bagsCount == "1" || bagsCount == "0") {
            binding.linearLayoutIfNoBuys.visibility = View.VISIBLE
        } else {
            binding.linearLayoutIfNoBuys.visibility = View.GONE
            getFavorites(object : FavoritesCallback {
                override fun onFavoritesReceived(favorites: List<ClothesCollection>) {
                    val uniqueFavorites = favorites.distinctBy { it.url }
                    favoritesList = uniqueFavorites
                    val adapter = ClothesCollectionBagAdapter(navController, favoritesList)
                    binding.recyclerViewBag.adapter = adapter
                }
            })

        }

        navController.addOnDestinationChangedListener{_,destination,_ ->
            destination.label = "Корзина"
            binding.toolbar.title = destination.label
        }

        return view
    }

    private fun getFavorites(callback: FavoritesCallback) {
        val userId = numberOfAuthUser
        val db = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("favorites")

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