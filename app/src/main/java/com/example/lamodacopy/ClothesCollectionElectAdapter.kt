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

class ClothesCollectionElectAdapter(val navController: NavController, val clothesCollection: List<ClothesCollection>): RecyclerView.Adapter<ClothesCollectionElectAdapter.ClothesCollectionElectViewHolder>() {

    class ClothesCollectionElectViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val textView = itemView.findViewById<TextView>(R.id.textView)
        val textView1 = itemView.findViewById<TextView>(R.id.textView1)
        val button = itemView.findViewById<Button>(R.id.buttonClick)
        val button1 = itemView.findViewById<Button>(R.id.buttonClick1)
        val button2 = itemView.findViewById<Button>(R.id.buttonClick2)
    }

    override fun getItemCount() = clothesCollection.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesCollectionElectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_of_buys, parent, false)
        return ClothesCollectionElectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClothesCollectionElectViewHolder, position: Int) {
        val imageData = clothesCollection[position]
        holder.button.setOnClickListener {
            val bundle = Bundle().apply {
                putString("IMAGE_URL", imageData.url)
                putString("DESCRIPTION", imageData.description)
                putString("COST", imageData.cost)
                putString("GENDER", imageData.gender)
            }
            navController.navigate(R.id.action_electFragment_to_describeClothFragment, bundle)
        }
        holder.button1.setOnClickListener {
            val bundle = Bundle().apply {
                putString("IMAGE_URL", imageData.url)
                putString("DESCRIPTION", imageData.description)
                putString("COST", imageData.cost)
                putString("GENDER", imageData.gender)
            }
            navController.navigate(R.id.action_electFragment_to_describeClothFragment, bundle)
        }
        holder.button2.setOnClickListener {
            val bundle = Bundle().apply {
                putString("IMAGE_URL", imageData.url)
                putString("DESCRIPTION", imageData.description)
                putString("COST", imageData.cost)
                putString("GENDER", imageData.gender)
            }
            navController.navigate(R.id.action_electFragment_to_describeClothFragment, bundle)
        }
        holder.textView.text = imageData.description
        holder.textView1.text = imageData.cost
        Glide.with(holder.imageView.context)
            .load(imageData.url)
            .into(holder.imageView)
    }
}