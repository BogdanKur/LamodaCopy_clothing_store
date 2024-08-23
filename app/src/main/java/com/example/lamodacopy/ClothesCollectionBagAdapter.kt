package com.example.lamodacopy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ClothesCollectionBagAdapter(private val navController: NavController, private val imageList: List<ClothesCollection>): RecyclerView.Adapter<ClothesCollectionBagAdapter.ClothesCollectionBagViewHolder>() {

    class ClothesCollectionBagViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val textView = itemView.findViewById<TextView>(R.id.textView)
        val textView1 = itemView.findViewById<TextView>(R.id.textView1)
        val button = itemView.findViewById<Button>(R.id.buttonClick)
        val button1 = itemView.findViewById<Button>(R.id.buttonClick1)
        val button2 = itemView.findViewById<Button>(R.id.buttonClick2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesCollectionBagViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_of_buys, parent, false)
        return ClothesCollectionBagViewHolder(view)
    }

    override fun getItemCount() = imageList.size

    override fun onBindViewHolder(holder: ClothesCollectionBagViewHolder, position: Int) {
        val imageData = imageList[position]
        holder.button.setOnClickListener {
            val bundle = Bundle().apply {
                putString("IMAGE_URL", imageData.url)
                putString("DESCRIPTION", imageData.description)
                putString("COST", imageData.cost)
                putString("GENDER", imageData.gender)
            }
            navController.navigate(R.id.action_bagFragment_to_describeClothFragment, bundle)
        }
        holder.button1.setOnClickListener {
            val bundle = Bundle().apply {
                putString("IMAGE_URL", imageData.url)
                putString("DESCRIPTION", imageData.description)
                putString("COST", imageData.cost)
                putString("GENDER", imageData.gender)
            }
            navController.navigate(R.id.action_bagFragment_to_describeClothFragment, bundle)
        }
        holder.button2.setOnClickListener {
            val bundle = Bundle().apply {
                putString("IMAGE_URL", imageData.url)
                putString("DESCRIPTION", imageData.description)
                putString("COST", imageData.cost)
                putString("GENDER", imageData.gender)
            }
            navController.navigate(R.id.action_bagFragment_to_describeClothFragment, bundle)
        }
        holder.textView.text = imageData.description
        holder.textView1.text = imageData.cost
        Glide.with(holder.imageView.context)
            .load(imageData.url)
            .into(holder.imageView)
    }
}