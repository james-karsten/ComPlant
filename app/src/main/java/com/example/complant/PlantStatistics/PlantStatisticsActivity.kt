package com.example.complant.PlantStatistics

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.complant.MainActivity
import com.example.complant.PlantPictures.PlantPicturesActivity
import com.example.complant.R
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_plant_statistics.*
import kotlin.math.sin

class PlantStatisticsActivity : AppCompatActivity() {

    private lateinit var series: LineGraphSeries<DataPoint>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_statistics)

        plotGraph()
        initViews()
    }

    private fun initViews() {
        buttonListeners()
    }

    private fun buttonListeners() {
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnPictures.setOnClickListener {
            val intent = Intent(this, PlantPicturesActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Example of how the graph should look like
     */
    private fun plotGraph() {
        var x = -0.5
        var y = -0.5

        val graph = findViewById<GraphView>(R.id.plantStatistics)
        series = LineGraphSeries<DataPoint>()

        for (i in 0 until 100) {
            x += 0.1
            y = sin(x)
            series.appendData(DataPoint(x, y), true, 100)
        }

        graph.addSeries(series)

        // settings ui graph
        graph.setBackgroundColor(Color.rgb(225, 225, 225))
        graph.gridLabelRenderer.gridColor = Color.rgb(192, 192, 192)
        series.thickness = 1
        series.color = Color.rgb(93, 146, 84)
        series.isDrawDataPoints = true
        series.dataPointsRadius = 5.toFloat()
    }
}
