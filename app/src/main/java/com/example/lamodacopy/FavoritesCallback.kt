package com.example.lamodacopy

interface FavoritesCallback {
    fun onFavoritesReceived(favoritesList: List<ClothesCollection>)
}