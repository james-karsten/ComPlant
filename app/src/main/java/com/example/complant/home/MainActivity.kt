package com.example.complant.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.complant.AddPlant.AddPlantActivity
import com.example.complant.PlantComparison.PlantComparisonActivity
import com.example.complant.PlantStatistics.PlantStatisticsActivity
import com.example.complant.R
import com.example.complant.model.Plant
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val plants = arrayListOf<Plant>()
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
    }

    private fun initViews() {
        rvPlants.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        rvPlants.adapter = plantAdapter
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

        btnStatistics.setOnClickListener {
            val intent = Intent(this, PlantStatisticsActivity::class.java)
            startActivity(intent)
        }
    }
}
