package com.example.complant.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.complant.R
import com.example.complant.model.Plant
import com.example.complant.model.PlantWithDays
import kotlinx.android.synthetic.main.plant_item.view.*
import java.time.LocalDate

class PlantAdapter(private val plants: List<Plant>) : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    var onClick: ((plant: Plant) -> Unit)? = null

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
                tvPlantKind.text = context.getString(R.string.tvPlantKind, plant.kind)
                tvPlantSince.text = context.getString(R.string.tvPlantSince, plant.since.toString())
                setOnClickListener { onClick?.invoke(plant) }
            }
        }
    }

}