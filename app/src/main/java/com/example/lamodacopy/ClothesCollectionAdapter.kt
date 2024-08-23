package com.example.lamodacopy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ClothesCollectionAdapter(private val navController: NavController, private var imageList: List<ClothesCollection>, private val itemClickListener: ItemClickListener): RecyclerView.Adapter<ClothesCollectionAdapter.ClothesCollectionViewHolder>() {
    private var notFilteredImageData: List<ClothesCollection> = imageList

    class ClothesCollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val textView1: TextView = itemView.findViewById(R.id.textView1)
        val button: Button = itemView.findViewById(R.id.buttonClick)
        val button1: Button = itemView.findViewById(R.id.buttonClick1)
        val button2: Button = itemView.findViewById(R.id.buttonClick2)
        val imageButton: ImageButton = itemView.findViewById(R.id.imageButton)
    }

    interface ItemClickListener {
        fun addToElect(item: ClothesCollection)
    }

    fun filter(query: String) {
        imageList = if (query.isEmpty()) {
            notFilteredImageData
        } else {
            notFilteredImageData.filter { it.description.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesCollectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_of_buys, parent, false)
        return ClothesCollectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClothesCollectionViewHolder, position: Int) {
        val imageData = imageList[position]
        holder.button.setOnClickListener {
            val bundle = Bundle().apply {
                putString("IMAGE_URL", imageData.url)
                putString("DESCRIPTION", imageData.description)
                putString("COST", imageData.cost)
                putString("GENDER", imageData.gender)
            }
            navController.navigate(R.id.action_mainFragment_to_describeClothFragment, bundle)
            Log.e("first", "s")
        }
        holder.button1.setOnClickListener {
            val bundle = Bundle().apply {
                putString("IMAGE_URL", imageData.url)
                putString("DESCRIPTION", imageData.description)
                putString("COST", imageData.cost)
                putString("GENDER", imageData.gender)
            }
            navController.navigate(R.id.action_mainFragment_to_describeClothFragment, bundle)
            Log.e("first", "s")
        }
        holder.button2.setOnClickListener {
            val bundle = Bundle().apply {
                putString("IMAGE_URL", imageData.url)
                putString("DESCRIPTION", imageData.description)
                putString("COST", imageData.cost)
                putString("GENDER", imageData.gender)
            }
            navController.navigate(R.id.action_mainFragment_to_describeClothFragment, bundle)
            Log.e("first", "s")
        }
        holder.imageButton.setOnClickListener{
            itemClickListener.addToElect(imageData)
            Log.e("second", "s")
        }
        holder.textView.text = imageData.description
        holder.textView1.text = imageData.cost
        Glide.with(holder.imageView.context)
            .load(imageData.url)
            .into(holder.imageView)
    }

    override fun getItemCount() = imageList.size
}