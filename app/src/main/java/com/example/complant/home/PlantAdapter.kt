package com.example.complant.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.complant.R
import com.example.complant.model.Plant
import kotlinx.android.synthetic.main.plant_item.view.*

class PlantAdapter(private val plants: List<Plant>) : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.plant_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return plants.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(plants[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind (plant: Plant) {
            itemView.run {
                tvPlantName.text = plant.name
                tvPlantLength.text = plant.length.toString()
                tvPlantGrowRate.text = plant.growRate.toString()
                tvPlantSince.text = plant.since.toString()
            }
        }
    }

}