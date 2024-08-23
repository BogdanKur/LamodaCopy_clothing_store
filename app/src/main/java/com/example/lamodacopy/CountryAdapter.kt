package com.example.lamodacopy

import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lamodacopy.DeliveryFragmentViewModel.Companion.btnTextDelivery
import com.example.lamodacopy.WhereAreYouFragment.Companion.editText

class CountryAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<CountryAdapter.CountryAdapterHolder>() {

    private var _citiesOfRussiaOver50k = listOf<String>()
    private var filteredCities = listOf<String>()

    var citiesOfRussiaOver50k: List<String>
        get() = _citiesOfRussiaOver50k
        set(value) {
            _citiesOfRussiaOver50k = value
            filteredCities = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = filteredCities.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryAdapterHolder =
        CountryAdapterHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: CountryAdapterHolder, position: Int) {
        val item = filteredCities[position]
        holder.bind(item, listener)
    }

    fun filter(query: String) {
        filteredCities = if (query.isEmpty()) {
            _citiesOfRussiaOver50k
        } else {
            _citiesOfRussiaOver50k.filter { it.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }

    class CountryAdapterHolder(val rootView: Button) : RecyclerView.ViewHolder(rootView) {
        val btnCountryChoose: Button = rootView.findViewById(R.id.btnCountryOne)

        companion object {
            fun inflateFrom(parent: ViewGroup): CountryAdapterHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.country_one, parent, false) as Button
                return CountryAdapterHolder(view)
            }
        }

        fun bind(item: String, listener: OnItemClickListener) {
            btnCountryChoose.text = item
            btnCountryChoose.setOnClickListener {
                editText?.setText(item)
                btnTextDelivery = item!!
                listener.onItemClicks()
            }
        }
    }
}