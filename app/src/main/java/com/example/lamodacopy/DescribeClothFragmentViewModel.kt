package com.example.lamodacopy

import android.util.Log
import androidx.lifecycle.ViewModel

class DescribeClothFragmentViewModel: ViewModel() {
    val usersRef = RegistrationFragment.users
    fun updateBagCount(userId: String, newCount: String, callback: (Boolean) -> Unit) {
        userId?.let { id ->
            usersRef.child(id).child("bagClothCount").setValue(newCount)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.e("Firebase", "Bag count updated successfully")
                        callback(true)
                    } else {
                        Log.e("Firebase", "Error updating bag count: ${task.exception?.message}")
                        callback(false)
                    }
                }
        }
    }

}