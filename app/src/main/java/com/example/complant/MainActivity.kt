package com.example.complant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.complant.AddPlant.AddPlantActivity
import com.example.complant.PlantComparison.PlantComparisonActivity
import com.example.complant.PlantStatistics.PlantStatisticsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.hide()

        initViews()
    }

    private fun initViews() {
        buttonListeners()
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
