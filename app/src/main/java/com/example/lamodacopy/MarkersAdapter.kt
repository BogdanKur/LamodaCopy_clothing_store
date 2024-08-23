package com.example.lamodacopy

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.Marker

class MarkersAdapter : RecyclerView.Adapter<MarkersAdapter.MarkersViewHolder>() {

    var citiesOfRussiaOver50k = listOf<List<String>>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var markers = listOf<Marker>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MarkersViewHolder(val rootView: Button) : RecyclerView.ViewHolder(rootView) {
        val btnFittingRoomsRecyclerView = rootView.findViewById<Button>(R.id.btnFittingRoomsRecyclerView)

        companion object {

            fun inflateFrom(parent: ViewGroup) : MarkersViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.markerlayout, parent, false) as Button
                return MarkersViewHolder(view)
            }

        }

        fun bind(item: List<String>) {
            val spannableButtons = mutableListOf<SpannableString>()
            val text = item.toString().replace("[", "").replace("]", "").trim()
            val spannableText = SpannableString(text)
            spannableText.setSpan(StyleSpan(Typeface.BOLD), 0, spannableText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableButtons.add(spannableText)
            if (spannableButtons.isNotEmpty()) {
                btnFittingRoomsRecyclerView.text = spannableButtons[0]
                btnFittingRoomsRecyclerView.gravity = Gravity.CENTER
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkersViewHolder = MarkersViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: MarkersViewHolder, position: Int) {
        val item = citiesOfRussiaOver50k[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = markers.size
}