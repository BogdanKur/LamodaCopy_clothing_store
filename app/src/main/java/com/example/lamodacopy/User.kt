package com.example.lamodacopy

import android.text.Editable

data class User(
    val name: String? = null,
    val tel: String? = null,
    val email: String? = null,
    val userId: Int? = null,
    var bagClothCount: String? = "1",
    var electClothCount: String? = "1",
    var country: String? = "Россия"

) {
    constructor() : this(null, null, null, null, "1") // Конструктор без аргументов
}

