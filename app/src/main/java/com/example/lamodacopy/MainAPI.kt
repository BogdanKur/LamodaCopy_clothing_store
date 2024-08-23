package com.example.lamodacopy

import com.google.android.gms.analytics.ecommerce.Product
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.File

interface MainAPI {

    @POST("save_user.php")
    suspend fun saveUser(@Body user: User)

    @GET("get_all_items.php")
    suspend fun getUsers(): List<User>
}