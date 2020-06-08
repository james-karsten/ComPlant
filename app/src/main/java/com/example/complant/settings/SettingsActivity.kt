package com.example.complant.settings

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import com.example.complant.PlantStatistics.CITY_REQUEST_CODE
import com.example.complant.PlantStatistics.PlantStatisticsActivity
import com.example.complant.R
import com.example.complant.home.MainActivity
import com.example.complant.home.PLANT_OBJECT
import com.example.complant.model.Plant
import kotlinx.android.synthetic.main.activity_settings.*

const val CITY = "CITY"

class SettingsActivity : AppCompatActivity() {

    private lateinit var plantFromStatistics: Plant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        initViews()
        buttonListeners()
    }

    /**
     * Save instance of saved api
     */
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.i("onSaveInstance: ", "called")
        outState.putString("location", etCityApi.text.toString())
        outState.putBoolean("useApi", toggleUseApi.isChecked)
    }

    override fun onResume() {
        super.onResume()
        Log.i("onResume called: ", "")

    }

    override fun onRestart() {
        super.onRestart()
        Log.i("onResume called: ", "")

    }

    override fun onStop() {
        super.onStop()
        Log.i("onStop called: ", "")
    }

    override fun onPause() {
        super.onPause()
        Log.i("onPause called: ", "")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("onDestroy called: ", "")

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        toggleUseApi.isChecked = savedInstanceState.getBoolean("useApi")
        etCityApi.setText(savedInstanceState.getString("location"))
    }

    private fun initViews() {
        this.plantFromStatistics = intent.getParcelableExtra("PLANT")!!
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
            sendCity()
        }
    }

    private fun sendCity() {
        if (toggleUseApi.isChecked) {
            val city = etCityApi.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra(CITY, city)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        } else {
            Toast.makeText(this, "Enable the weather api", Toast.LENGTH_SHORT).show()
        }
    }

}
