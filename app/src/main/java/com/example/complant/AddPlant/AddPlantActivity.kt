package com.example.complant.AddPlant

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.complant.home.MainActivity
import com.example.complant.R
import com.example.complant.model.Plant
import kotlinx.android.synthetic.main.activity_add_plant.*
import java.text.SimpleDateFormat
import java.util.*

class AddPlantActivity : AppCompatActivity() {

    private val formatOfDate = SimpleDateFormat("dd-MM-yy", Locale.getDefault())
    private val viewModel: AddPlantActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plant)
        actionBar?.hide()

        initViews()
        onClickButtons()
    }

    private fun onClickButtons() {
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btnAddPlantToDatabase.setOnClickListener {savePlant()}
        etDayOfSeeded.setOnClickListener { pickDate() }
    }

    private fun initViews() {
        viewModel.success.observe(this, androidx.lifecycle.Observer { success ->
            if(success!!) {
                Toast.makeText(this, "Plant is saved", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please try again", Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }

    private fun savePlant() {
        if (plantIsValid()) {
            val date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).parse(etDayOfSeeded.text.toString())

            val plant =  Plant(
                name = etName.text.toString(),
                length = etLength.text.toString().toDouble(),
                kind = etKind.text.toString(),
                since = date,
                growRate = 0.0,
                days = emptyList()
            )
            viewModel.insertPlant(plant)
        } else {
            Toast.makeText(this, "Fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun plantIsValid() : Boolean {
        return etName.text.isNotBlank() && etKind.text.isNotBlank() && etLength.text.isNotBlank() &&
                etDayOfSeeded.text.isNotBlank()
    }

    private fun pickDate() {
        val now = Calendar.getInstance()
        val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

            val pickedDate = Calendar.getInstance()

            pickedDate.set(Calendar.YEAR, year)
            pickedDate.set(Calendar.MONTH, month)
            pickedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)


            etDayOfSeeded.setText(formatOfDate.format(pickedDate.time))
        }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
        datePicker.show()
    }
}
