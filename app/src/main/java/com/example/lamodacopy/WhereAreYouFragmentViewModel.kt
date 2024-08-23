package com.example.lamodacopy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WhereAreYouFragmentViewModel: ViewModel() {
    val citiesOfRussiaOver50k: LiveData<List<String>> = MutableLiveData(listOf(
        "Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург",
        "Нижний Новгород", "Казань", "Челябинск", "Омск", "Самара", "Ростов-на-Дону",
        "Уфа", "Красноярск", "Воронеж", "Пермь", "Волгоград", "Краснодар",
        "Тюмень", "Тольятти", "Ижевск", "Ульяновск", "Барнаул", "Владивосток",
        "Ярославль", "Иркутск", "Тверь", "Брянск"
    ))


}