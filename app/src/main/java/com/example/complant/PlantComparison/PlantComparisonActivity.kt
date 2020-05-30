package com.example.complant.PlantComparison

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.complant.home.MainActivity
import com.example.complant.R
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_plant_comparison.*

class PlantComparisonActivity : AppCompatActivity() {

    private lateinit var plant1: LineGraphSeries<DataPoint>
    private lateinit var plant2: LineGraphSeries<DataPoint>
    private lateinit var plant3: LineGraphSeries<DataPoint>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_comparison)

        initViews()
        plotGraph()
    }

    private fun initViews() {
        buttonListeners()
    }

    private fun buttonListeners() {
        btnBackComparison.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Example of how the graph comparison should look like
     */
    private fun plotGraph() {
        var x = -0.5
        var y = -0.5

        val graph = findViewById<GraphView>(R.id.plantComparison)
        plant1 = LineGraphSeries<DataPoint>()
        plant2 = LineGraphSeries<DataPoint>()
        plant3 = LineGraphSeries<DataPoint>()

        for (i in 0 until 100) {
            x += 0.1
            y += 0.2 * x
            plant1.appendData(DataPoint(x, y), true, 500)
        }

        for (i in 0 until 100) {
            x += 0.3
            y += 0.5 * x
            plant2.appendData(DataPoint(x, y), true, 500)
        }

        for (i in 0 until 100) {
            x += 0.8
            y += 0.7 * x
            plant3.appendData(DataPoint(x, y), true, 500)
        }

        graph.addSeries(plant1)
        graph.addSeries(plant2)
        graph.addSeries(plant3)

        // settings ui graph
        graph.setBackgroundColor(Color.rgb(225, 225, 225))
        graph.gridLabelRenderer.horizontalAxisTitle = "time"
        graph.gridLabelRenderer.verticalAxisTitle = "length"
        graph.gridLabelRenderer.gridColor = Color.rgb(192, 192, 192)
        plant1.thickness = 1
        plant1.color = Color.rgb(93, 146, 84)
        plant1.isDrawDataPoints = true
        plant1.dataPointsRadius = 5.toFloat()

        plant2.thickness = 1
        plant2.color = Color.rgb(158, 59, 59)
        plant2.isDrawDataPoints = true
        plant2.dataPointsRadius = 5.toFloat()

        plant3.thickness = 1
        plant3.color = Color.rgb(86, 84, 146)
        plant3.isDrawDataPoints = true
        plant3.dataPointsRadius = 5.toFloat()
    }
}
