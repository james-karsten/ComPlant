package com.example.complant.PlantPictures

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.complant.PlantStatistics.PlantStatisticsActivity
import com.example.complant.R
import kotlinx.android.synthetic.main.activity_plant_pictures.*

class PlantPicturesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_pictures)

        initViews()
    }

    private fun initViews() {
        buttonListeners()
    }

    private fun buttonListeners() {
        btnBackPictures.setOnClickListener {
            val intent = Intent(this, PlantStatisticsActivity::class.java)
            startActivity(intent)
        }
    }
}
