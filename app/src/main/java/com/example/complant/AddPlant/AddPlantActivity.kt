package com.example.complant.AddPlant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.complant.MainActivity
import com.example.complant.R
import kotlinx.android.synthetic.main.activity_add_plant.*

class AddPlantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plant)
        actionBar?.hide()

        initViews()
    }

    private fun initViews() {
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
