package com.example.lamodacopy

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.lamodacopy.ProfileFragment.Companion.numberOfAuthUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class RegistrationFragmentViewModel: ViewModel() {
    fun addNewUser(etNameReg: String, etEmailReg: String, etNumberReg: String) {
        RegistrationFragment.users.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var maxUserId = 0
                for (childSnapshot in snapshot.children) {
                    val userId = childSnapshot.child("userId").getValue<Int?>()
                    if (userId != null && userId > maxUserId) {
                        maxUserId = userId
                    }
                }

                val newUser = User(etNameReg, etNumberReg, etEmailReg, maxUserId + 1)
                numberOfAuthUser = (maxUserId + 1).toString()
                RegistrationFragment.users.child((maxUserId + 1).toString()).setValue(newUser)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("Firebase", "User added successfully")
                        } else {
                            Log.w("Firebase", "Error adding user", task.exception)
                        }
                    }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Firebase", "Error getting users", error.toException())
            }
        })
    }

}