package com.example.complant.PlantComparison

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.complant.R
import com.example.complant.home.MainActivity
import com.example.complant.model.Day
import com.example.complant.model.Plant
import com.example.complant.model.PlantWithDays
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_plant_comparison.*
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.properties.Delegates


class PlantComparisonActivity : AppCompatActivity() {

    private val plantComparisonActivityViewModel: PlantComparisonActivityViewModel by viewModels()
    private val plantsWithDays = arrayListOf<PlantWithDays>()
    private lateinit var graph: GraphView
    private var currColor by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_comparison)
        actionBar?.hide()

        configureGraph()
        initViews()
        observeViewModel()
    }

    /**
     * Get all plants and their days from the ViewModel
     */
    private fun observeViewModel() {
        plantComparisonActivityViewModel.plantsAndDays.observe(this, Observer {
            this.plantsWithDays.clear()
            this.plantsWithDays.addAll(it)

            /* add a line for every plant */
            for (i in 0 until plantsWithDays.size) {
                plotPlant(plantsWithDays[i].plant, plantsWithDays[i].days)
            }
        })
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
     * Plots a line for one specific plant
     */
    private fun plotPlant(plant: Plant, days: List<Day>) {

        /* properties of current plant */
        val plantLine = LineGraphSeries<DataPoint>()
        val amountOfDays = days.size
        var length: Double
        var currDay = 0.0

        /* add all data to the datapoint*/
        for (i in 0 until amountOfDays) {
            currDay += 1
            length = days[i].dayLength

            plantLine.appendData(DataPoint(currDay, length), true, 1000)
        }

        /* settings for the line */
        plantLine.thickness = 5
        currColor = Color.rgb((0..258).random(), (0..258).random(), (0..258).random())
        plantLine.color = currColor
        plantLine.isDrawDataPoints = true
        plantLine.dataPointsRadius = 5.toFloat()

        /* add Line to graph*/
        graph.addSeries(plantLine)

        /* calculate average nutrition of current plant */
        calculateAverageNutrition(plant, days)
    }

    /**
     * Configures the graph
     */
    private fun configureGraph() {
        // settings ui graph
        graph = findViewById(R.id.plantComparison)
        graph.setBackgroundColor(Color.rgb(225, 225, 225))
        graph.gridLabelRenderer.gridColor = Color.rgb(192, 192, 192)

        // set manual X bounds
        graph.viewport.isYAxisBoundsManual = true
        graph.viewport.setMinY(0.0)
        graph.viewport.setMaxY(300.0)

        graph.viewport.isXAxisBoundsManual = true
        graph.viewport.setMinX(0.0)
        graph.viewport.setMaxX(50.0)


        // enable scaling and scrolling
        graph.viewport.setScalableY(true)
        graph.viewport.isScalable = true

        // x/y-axis information
        graph.gridLabelRenderer.horizontalAxisTitle = "time (days)"
        graph.gridLabelRenderer.verticalAxisTitle = "length (cm)"
    }

    /**
     * Example of how the graph comparison should look like
     */
    private fun calculateAverageNutrition(plant: Plant, days: List<Day>) {
        var averageWater = 0.0
        var averageSun = 0.0
        var averageTemp = 0.0

        val decimalFormat = DecimalFormat("#.##")
        decimalFormat.roundingMode = RoundingMode.CEILING

        /*TODO hoe kan je dit berekenen? */
        var averageGrow = 0.0

        /* accumulates the nutrition data */
        for (i in days.indices) {
            averageWater += days[i].dayWater
            averageSun += days[i].daySun
            averageTemp += days[i].dayTemperature
            averageGrow += days[i].dayLength
        }

        averageWater /= days.size
        averageSun /= days.size
        averageTemp /= days.size
        averageGrow /= days.size

        addRow(plant, decimalFormat.format(averageWater), decimalFormat.format(averageSun),
            decimalFormat.format(averageTemp), decimalFormat.format(averageGrow))
    }

    /**
     * Adds a row for each plant that shows the
     * average nutrition it received.
     */
    private fun addRow(plant: Plant, averageWater: String, averageSun: String, averageTemp: String, averageGrowRate: String) {

        val tableRowAverage = TableRow(this)
        tableRowAverage.setPadding(30, 30, 10, 30)

        tableRowAverage.layoutParams = TableLayout.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )

        val name = TextView(this)
        name.text = plant.name
        name.setTextColor(currColor)
        name.textSize = 10f
        tableRowAverage.addView(name) // add the column to the table row here

        val tvAverageWater = TextView(this)
        tvAverageWater.text = "$averageWater"
        tvAverageWater.textSize = 15f
        tableRowAverage.addView(tvAverageWater) // add the column to the table row here

        val tvAverageTemp = TextView(this)
        tvAverageTemp.text = "$averageTemp"
        tvAverageTemp.textSize = 15f
        tableRowAverage.addView(tvAverageTemp) // add the column to the table row here

        val tvAverageSun = TextView(this)
        tvAverageSun.text = "$averageSun"
        tvAverageSun.textSize = 15f
        tableRowAverage.addView(tvAverageSun) // add the column to the table row here

        val tvGrowRate = TextView(this)
        tvGrowRate.text = "$averageGrowRate"
        tvGrowRate.textSize = 15f
        tableRowAverage.addView(tvGrowRate) // add the column to the table row here

        /* add row to table layout */
        tableLayout.addView(
            tableRowAverage, TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT
            )
        )
    }
}
