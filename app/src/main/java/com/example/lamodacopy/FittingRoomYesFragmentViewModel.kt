package com.example.lamodacopy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class FittingRoomYesFragmentViewModel: ViewModel() {

    fun markersList(currentCountry: String, googleMap: GoogleMap): List<Marker> {
        val markers = mutableListOf<Marker>()
        if(currentCountry == "Москва") {
            val marker1 = LatLng(55.576493, 38.207595)
            val markerOptions1 = MarkerOptions().position(marker1).title("Раменское")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Санкт-Петербург") {
            val marker1 = LatLng(60.710113, 28.751641)
            val markerOptions1 = MarkerOptions().position(marker1).title("Выборг")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)

            val marker2 = LatLng(59.377778, 28.616848)
            val markerOptions2 = MarkerOptions().position(marker2).title("Кингисепп")
            val markers2 = googleMap.addMarker(markerOptions2)
            markers.add(markers2!!)
        }

        if(currentCountry == "Новосибирск") {
            val marker1 = LatLng(54.838841, 83.107162)
            val markerOptions1 = MarkerOptions().position(marker1).title("Морской")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Екатеринбург") {
            val marker1 = LatLng(56.8423, 60.645157)
            val markerOptions1 = MarkerOptions().position(marker1).title("Гагарина")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Нижний Новгород") {
            val marker1 = LatLng(56.353754, 43.867233)
            val markerOptions1 = MarkerOptions().position(marker1).title("Коминтерна")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Казань") {
            val marker1 = LatLng(55.78323, 49.128782)
            val markerOptions1 = MarkerOptions().position(marker1).title("Казань")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Челябинск") {
            val marker1 = LatLng(55.145617, 61.383114)
            val markerOptions1 = MarkerOptions().position(marker1).title("Челябинск")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Омск") {
            val marker1 = LatLng(55.028197, 73.278479)
            val markerOptions1 = MarkerOptions().position(marker1).title("Омск")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Самара") {
            val marker1 = LatLng(53.210118, 50.15756)
            val markerOptions1 = MarkerOptions().position(marker1).title("Самара")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Ростов-на-Дону") {
            val marker1 = LatLng(47.232426, 39.703182)
            val markerOptions1 = MarkerOptions().position(marker1).title("Ростов-на-Дону")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Уфа") {
            val marker1 = LatLng(54.733983, 55.964898)
            val markerOptions1 = MarkerOptions().position(marker1).title("Уфа")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Красноярск") {
            val marker1 = LatLng(56.011937,92.865534)
            val markerOptions1 = MarkerOptions().position(marker1).title("Красноярск")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Воронеж") {
            val marker1 = LatLng(51.670272,39.190199)
            val markerOptions1 = MarkerOptions().position(marker1).title("Воронеж")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Пермь") {
            val marker1 = LatLng(58.011714,56.245604)
            val markerOptions1 = MarkerOptions().position(marker1).title("Пермь")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Волгоград") {
            val marker1 = LatLng(48.678854,44.446125)
            val markerOptions1 = MarkerOptions().position(marker1).title("Волгоград")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Краснодар") {
            val marker1 = LatLng(45.011363,39.07163)
            val markerOptions1 = MarkerOptions().position(marker1).title("Краснодар")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Тюмень") {
            val marker1 = LatLng(57.108956,65.570287)
            val markerOptions1 = MarkerOptions().position(marker1).title("Тюмень")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Тольятти") {
            val marker1 = LatLng(53.514803,49.411482)
            val markerOptions1 = MarkerOptions().position(marker1).title("Тольятти")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Ижевск") {
            val marker1 = LatLng(56.842192,53.231406)
            val markerOptions1 = MarkerOptions().position(marker1).title("Ижевск")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Ульяновск") {
            val marker1 = LatLng(54.272567,48.2997)
            val markerOptions1 = MarkerOptions().position(marker1).title("Ульяновск")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Барнаул") {
            val marker1 = LatLng(53.348026,83.671179)
            val markerOptions1 = MarkerOptions().position(marker1).title("Барнаул")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Владивосток") {
            val marker1 = LatLng(43.091036,131.970555)
            val markerOptions1 = MarkerOptions().position(marker1).title("Владивосток")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Ярославль") {
            val marker1 = LatLng(57.626337,39.868454)
            val markerOptions1 = MarkerOptions().position(marker1).title("Ярославль")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Иркутск") {
            val marker1 = LatLng(52.278283,104.283077)
            val markerOptions1 = MarkerOptions().position(marker1).title("Иркутск")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Тверь") {
            val marker1 = LatLng(56.880415,35.84596)
            val markerOptions1 = MarkerOptions().position(marker1).title("Тверь")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)
        }

        if(currentCountry == "Брянск") {
            val marker1 = LatLng(53.264965,34.336682)
            val markerOptions1 = MarkerOptions().position(marker1).title("Брянск1")
            val markers1 = googleMap.addMarker(markerOptions1)
            markers.add(markers1!!)

            val marker2 = LatLng(53.310915,34.305942)
            val markerOptions2 = MarkerOptions().position(marker2).title("Брянск2")
            val markers2 = googleMap.addMarker(markerOptions2)
            markers.add(markers2!!)

            val marker3 = LatLng(53.268789,34.406499)
            val markerOptions3 = MarkerOptions().position(marker3).title("Брянск3")
            val markers3 = googleMap.addMarker(markerOptions3)
            markers.add(markers3!!)
        }


        return markers
    }


    fun whoseCountry(currentCountry: String) : List<List<String>> {
        if(currentCountry == "Москва") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Раменское|Чугунова,15А", "Чугунова, 15а", "Доступна примерка", "пн-вс:10:00-21:00"),
                listOf("Lamoda|Зеленоград|Корпус 2024", "Корпус, 2024(Крюково)", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Санкт-Петербург") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Выборг|Мира,4", "Мира,4", "Доступна примерка", "пн-вс:09:00-21:00"),
                listOf("Lamoda|Кингисеп|Воровского, 34", "Воровского, 34", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Новосибирск") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Новосибирск|пр-т Морской д.19", "проспект Морской, 19", "Доступна примерка", "пн-вс:09:00-20:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Екатеринбург") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Екатеринбург|пр-т Ленина д.70/Гагарина д.18", "Гагарина д.18", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Нижний Новгород") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Нижний Новгород|Коминтерна, 139", "Коминтерна, 139", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Казань") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Казань|Петербургская, 40", "Петербургская, 40", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Челябинск") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Челябинск|Доватора, 33", "Доватора, 33", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Омск") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Омск|пр-т Культуры, 6", "пр-т Культуры, 6", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Самара") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Самара|пр-т Масленникова, 16", "пр-т Масленникова, 16", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Ростов-на-Дону") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Ростов-на-Дону|пр-т Космонавтов, 17", "пр-т Космонавтов, 17", "Доступна примерка", "пн-вс:09:00-20:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Уфа") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Уфа|Революционная, 107", "Революционная, 107", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Красноярск") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Красноярск|пр-т Мира, 80", "пр-т Мира, 80", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Воронеж") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Воронеж|Плехановская, 46", "Плехановская, 46", "Доступна примерка", "пн-вс:09:00-20:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Пермь") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Пермь|Пермская, 68", "Пермская, 68", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Волгоград") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Волгоград|Университетский пр-т, 15", "Университетский пр-т, 15", "Доступна примерка", "пн-вс:09:00-20:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Краснодар") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Краснодар|Игнатова, 2", "Игнатова, 2", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Тюмень") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Тюмень|Василия Гольцова, 15", "Василия Гольцова, 15", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Тольятти") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Тольятти|Молодежный 4", "Молодежный 4", "Доступна примерка", "пн-вс:10:00-20:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Ижевск") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Ижевск|Воровского,143", "Воровского,143", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Ульяновск") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Ульяновск|Камышинская,25,корп 1", "Камышинская,25,корп 1", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Барнаул") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Барнаул|Попова 158", "Попова 158", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Владивосток") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Владивосток|ул.Новоивановская,3", "Новоивановская,3", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Ярославль") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Ярославль|ул.Свободы, 46", "Свободы, 46", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Иркутск") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Иркутск|ул.Ленина, 40", "Ленина, 40", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        if(currentCountry == "Тверь") {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Тверь|пр-т Ленина, 14", "пр-т Ленина, 14", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }

        else {
            val fittingRooms: List<List<String>> = listOf(
                listOf("Lamoda|Брянск|3 Интернационала, 11", "3 Интернационала, 11", "Доступна примерка", "пн-вс:10:00-21:00"),
                listOf("Lamoda|Брянск|Советская, 108", "Советская, 108", "Доступна примерка", "пн-вс:10:00-21:00"),
                listOf("Lamoda|Брянск|ул.Пушкина,43", "ул.Пушкина,43", "Доступна примерка", "пн-вс:10:00-21:00")
            )
            return fittingRooms
        }



    }

}