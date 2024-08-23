package com.example.lamodacopy

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lamodacopy.ProfileFragment.Companion.numberOfAuthUser
import com.example.lamodacopy.ProfileFragment.Companion.uses
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileFragmentViewModel : ViewModel() {
    private val usersRef = RegistrationFragment.users
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users
    private var userId: String? = null

    private val _percentSale = MutableLiveData<Int>(0)
    val percentSale: LiveData<Int>
        get() = _percentSale

    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    var count = 0

    fun startUpdating() {
        runnable = object : Runnable {
            override fun run() {
                usersRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val userList = mutableListOf<User>()
                        for (userSnapshot in snapshot.children) {
                            val user = userSnapshot.getValue<User>()
                            user?.let { userList.add(it) }
                        }
                        _users.value = userList
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("Firebase", "Error getting users: ${error.message}")
                    }
                })

                usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (userSnapshot in snapshot.children) {
                            val userIdValue = userSnapshot.child("userId").getValue<Int>()



                            if (userIdValue != null) {
                                userId = userIdValue.toString()
                                Log.e("Debug", userId!!)
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("Firebase", "Error getting users: ${error.message}")
                    }
                })
                count++
                Log.e("count", count.toString())
                handler.postDelayed(this, 1000)
                if(count >= 4) {
                    stopUpdating()
                }
            }
        }
        if(count >= 4) {
            stopUpdating()
        }
        handler.post(runnable)
    }
    fun stopUpdating() {
        handler.removeCallbacks(runnable)
    }

    init {
        startUpdating()

        if(count >= 4) {
            stopUpdating()
        }
    }

    fun getName(userId: String?, callback: (String?) -> Unit) {
        userId?.let { id->
            usersRef.child(id).child("name").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val name = snapshot.getValue(String::class.java)
                    callback(name)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Firebase", "Error getting user name: ${error.message}")
                    callback(null)
                }
            })
        }
    }

    fun getFirst(userId: String?, callback: (String?) -> Unit) {
        userId?.let { id ->
            usersRef.child(id).child("name").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val name = snapshot.getValue(String::class.java)
                    val firstLetter = name?.firstOrNull()?.toString() // Получить первую букву имени
                    Log.e("FirstLetter", firstLetter ?: "Unknown")
                    callback(firstLetter)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Firebase", "Error getting user name: ${error.message}")
                    callback(null)
                }
            })
        } ?: run {
            Log.e("FirstLetter", "User ID is null")
            callback(null)
        }
    }
}