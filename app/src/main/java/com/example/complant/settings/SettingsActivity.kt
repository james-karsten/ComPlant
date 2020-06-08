package com.example.complant.settings

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.complant.PlantStatistics.PlantStatisticsActivity
import com.example.complant.R
import com.example.complant.home.PLANT_OBJECT
import com.example.complant.model.Plant
import kotlinx.android.synthetic.main.activity_settings.*

const val UPDATED_PLANT = "UPDATED_PLANT"

class SettingsActivity : AppCompatActivity() {

    private val settingsActivityViewModel: SettingsActivityViewModel by viewModels()
    private lateinit var plantFromStatistics: Plant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        initViews()
        buttonListeners()
    }

    private fun initViews() {
        /* get api settings */
        this.plantFromStatistics = intent.getParcelableExtra("PLANT")!!
        if (plantFromStatistics.useApi) {
            toggleUseApi.isChecked = plantFromStatistics.useApi
            etCityApi.setText(plantFromStatistics.city)
        }
    }

    private fun buttonListeners() {

        /* check at start of activity */
        if (!toggleUseApi.isChecked) {
            etCityApi.isEnabled = false
            etCityApi.setBackgroundColor(Color.rgb(250, 215, 215))
        }

        toggleUseApi.setOnClickListener {
            etCityApi.isEnabled = toggleUseApi.isChecked
            etCityApi.setBackgroundColor(Color.rgb(218, 218, 218))
            if (!toggleUseApi.isChecked) {
                etCityApi.setText("")
                etCityApi.setBackgroundColor(Color.rgb(250, 215, 215))

            }
        }

        btnBackSettings.setOnClickListener {
            val intent = Intent(this, PlantStatisticsActivity::class.java)
            intent.putExtra(PLANT_OBJECT, this.plantFromStatistics)
            startActivity(intent)
        }

        btnSaveSettings.setOnClickListener {
            this.plantFromStatistics.useApi = toggleUseApi.isChecked
            this.plantFromStatistics.city = etCityApi.text.toString()

            if ((toggleUseApi.isChecked && etCityApi.text.isNotBlank()) or (!toggleUseApi.isChecked)) {
                settingsActivityViewModel.updatePlant(plantFromStatistics)
                sendCity()
            } else {
                Toast.makeText(this, "please put in a city", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendCity() {
        val resultIntent = Intent()
        resultIntent.putExtra(UPDATED_PLANT, this.plantFromStatistics)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

}
