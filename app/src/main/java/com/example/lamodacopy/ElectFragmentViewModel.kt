package com.example.lamodacopy

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ElectFragmentViewModel: ViewModel() {
    private val usersRef = RegistrationFragment.users
    fun getBagCount(userId: String, callback: (String?) -> Unit) {
        userId?.let{ id ->
            usersRef.child(id).child("electClothCount").addListenerForSingleValueEvent(object:
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val bagCount = snapshot.getValue(String::class.java)
                    if(bagCount == "0") {
                        callback("1")
                    } else {
                        callback(bagCount)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.e("Firebase", "Error getting user name: ${error.message}")
                    callback(null)
                }

            })
        }
    }
}