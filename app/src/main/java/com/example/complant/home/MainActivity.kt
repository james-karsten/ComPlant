package com.example.complant.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.complant.AddPlant.AddPlantActivity
import com.example.complant.PlantComparison.PlantComparisonActivity
import com.example.complant.PlantStatistics.PlantStatisticsActivity
import com.example.complant.R
import com.example.complant.model.Plant
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.complant.model.PlantWithDays

const val PLANT_OBJECT = "PLANT_OBJECT"

class MainActivity : AppCompatActivity() {

    private val plants = arrayListOf<Plant>()
    private val plantsWithDays = arrayListOf<PlantWithDays>()
    private val plantAdapter = PlantAdapter(plants)
    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.hide()

        initViews()
        buttonListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        mainActivityViewModel.plants.observe(this, Observer { plants ->
            this@MainActivity.plants.clear()
            this@MainActivity.plants.addAll(plants)
            plantAdapter.notifyDataSetChanged()
        })

//        mainActivityViewModel.plantsWithDays.observe(this, Observer { plantsWithDays ->
//            this@MainActivity.plantsWithDays.clear()
//            this@MainActivity.plantsWithDays.addAll(plantsWithDays)
//            plantAdapter.notifyDataSetChanged()
//            Log.i("PlantWithDays:", "${plantsWithDays.size}")
//        })
    }

    private fun initViews() {
        rvPlants.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        rvPlants.adapter = plantAdapter
        createItemTouchHelper().attachToRecyclerView(rvPlants)

        plantAdapter.onClick = {
            val intent = Intent(this, PlantStatisticsActivity::class.java)
            intent.putExtra(PLANT_OBJECT, it)
            startActivity(intent)
        }
    }

    private fun buttonListeners() {
        btnAddPlant.setOnClickListener {
            val intent = Intent(this, AddPlantActivity::class.java)
            startActivity(intent)
        }

        btnCompare.setOnClickListener {
            val intent = Intent(this, PlantComparisonActivity::class.java)
            startActivity(intent)
        }

    }

    private fun createItemTouchHelper() : ItemTouchHelper {
        val callback = object: ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            /* delete plant when swiping */
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val plantToDelete = plants[position]
                mainActivityViewModel.deletePlant(plantToDelete)
            }
        }

        return ItemTouchHelper(callback)
    }

}
