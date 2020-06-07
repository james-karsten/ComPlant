package com.example.complant.PlantStatistics

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.complant.R
import com.example.complant.home.MainActivity
import com.example.complant.home.PLANT_OBJECT
import com.example.complant.model.Day
import com.example.complant.model.Plant
import com.example.complant.model.PlantWithDays
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_plant_statistics.*

class PlantStatisticsActivity : AppCompatActivity() {

    private lateinit var tableLayout: TableLayout
    private val plantStatisticsActivityViewModel: PlantStatisticsActivityViewModel by viewModels()
    private lateinit var series: LineGraphSeries<DataPoint>
    private lateinit var plant: Plant
    private val plantsWithDays = arrayListOf<PlantWithDays>()
    private var alreadyExecuted = false
    private var plantPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_statistics)
        actionBar?.hide()

        initViews()
        observeViewModel()
//        plotGraph()
        buttonListeners()
    }

    private fun initViews() {
        tableLayout = findViewById(R.id.tableLayout)
        plant = intent.getParcelableExtra(PLANT_OBJECT)!!
    }

    private fun observeViewModel() {
        plantStatisticsActivityViewModel.plantsAndDays.observe(this, Observer {
            plantsWithDays.clear()
            plantsWithDays.addAll(it)
            /* load data only once */
            if (!alreadyExecuted) {
                addDayRowAndData(it)
                alreadyExecuted = true
            }

            plotGraph(it)
        })
    }


    private fun addDayRowAndData(plantWithDays: List<PlantWithDays>) {

        var counter = 0
        var rowsToAdd = 0

        /* get plant position in list */
        for (plant in plantWithDays) {
            if (plant.plant == this.plant) {
                this.plantPosition = plantWithDays.indexOf(plant)
                Log.i("Plant pos ", plantPosition.toString())
            }
        }

        /* check if there are days in the list, otherwise no rows have to be added */
        if (plantWithDays[plantPosition].days.isNotEmpty()) {

            /* loop thru days of the plant */
            for (i in plantWithDays[plantPosition].days) {
//                /**
//                 * set data of first day in first row
//                 * Because the first row is standard in this view
//                 */
//                if (plantWithDays[plantPosition].days.elementAt(counter) ==
//                    plantWithDays[plantPosition].days.elementAt(0)) {
//
//                    /* first day of plant*/
//                    val day = plantWithDays[plantPosition].days.elementAt(0)
//                    etWater.setText(day.dayWater.toString())
//                    etTemp.setText(day.dayTemperature.toString())
//                    etLength.setText(day.dayLength.toString())
//                    etSun.setText(day.daySun.toString())
//                }
                /*TODO counter? */
//                counter++
//                addRow(counter+1)

                /* -1 because the first row is already added*/
                rowsToAdd = plantWithDays[plantPosition].days.size
                Log.i("Day", rowsToAdd.toString())

            }

            /* until, because the view has already one row standard */
            for (i in 1..rowsToAdd) {
                addRow(i, plantWithDays[plantPosition].days.elementAt(i-1))
            }
        }

    }

    /**
     * Gets the last day saved from the days list
     */
    private fun getLastDay() : Int {
        val lastDay = plantsWithDays[plantPosition].days.size
        if (lastDay == 0) {
            return 1
        }
        return lastDay+1
    }

    /**
     * TODO layout verbeteren
     * Adds a new row when a day is saved
     */
    private fun addRow(lastDay: Int, dayOfRow: Day) {
        val tableRowStatistics = TableRow(this)
        tableRowStatistics.setPadding(2, 2, 2, 2)

        tableRowStatistics.layoutParams = TableLayout.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )

        val day = TextView(this)
        day.text = "Day $lastDay"
        day.textSize = 10f
        tableRowStatistics.addView(day) // add the column to the table row here

        val water = LayoutInflater.from(this).inflate(R.layout.edittext, null) as EditText
        water.setText(dayOfRow.dayWater.toString())
        tableRowStatistics.addView(water)

        val temp = LayoutInflater.from(this).inflate(R.layout.edittext, null) as EditText
        temp.setText(dayOfRow.dayTemperature.toString())
        tableRowStatistics.addView(temp)

        val sun = LayoutInflater.from(this).inflate(R.layout.edittext, null) as EditText
        sun.setText(dayOfRow.daySun.toString())
        tableRowStatistics.addView(sun)

        val length = LayoutInflater.from(this).inflate(R.layout.edittext, null)as EditText
        length.setText(dayOfRow.dayLength.toString())
        tableRowStatistics.addView(length)


        val addButton = Button(this)
        addButton.gravity = Gravity.START
        addButton.stateListAnimator = null
        addButton.setBackgroundColor(Color.rgb(239,239,239))
        addButton.text = "save"
        addButton.textSize = 10f
        addButton.setTextColor(Color.rgb(93,146,84))
        tableRowStatistics.addView(addButton)


        /* Get current day */
        var currDay: Int

        /* Save edited day */
        addButton.setOnClickListener {
            currDay = plantsWithDays[plantPosition].days.indexOf(dayOfRow)+1
            Log.i("cuurDay pos:", currDay.toString())
            Log.i("length:", plantsWithDays[plantPosition].days.size.toString())
            Log.i("cuurDay pos:", (currDay+1).toString())


            /* Save all changed values */
            dayOfRow.dayLength = length.text.toString().toDouble()
            dayOfRow.daySun = sun.text.toString().toDouble()
            dayOfRow.dayTemperature = temp.text.toString().toDouble()
            dayOfRow.dayWater = water.text.toString().toDouble()

            /* update day */
            plantStatisticsActivityViewModel.updateDay(dayOfRow)

            /* notify user */
            Toast.makeText(this, "Updated day $currDay", Toast.LENGTH_SHORT).show()
        }

        /* add row to table layout */
        tableLayout.addView(
            tableRowStatistics, TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT
            )
        )
    }

    private fun buttonListeners() {
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        /* TODO implement pictures for plant */
        btnPictures.setOnClickListener {
//            val intent = Intent(this, PlantPicturesActivity::class.java)
//            startActivity(intent)
        }

        btnNewAdd.setOnClickListener { addDay() }
    }

    /**
     * Adds a day to the room database and
     * connects it to the current plant
     */
    private fun addDay() {
        if (checkValues()) {
            val day = Day(
                dayNumber = 1,
                dayLength = etNewLength.text.toString().toDouble(),
                daySun = etNewSun.text.toString().toDouble(),
                dayWater = etNewWater.text.toString().toDouble(),
                dayTemperature = etNewTemp.text.toString().toDouble(),
                dayPlantId = plant.plantId
            )

            /* TODO update values in itemViewHolder*/
            /* update the length of the plant from the last day */
            plant.length = day.dayLength
            plantStatisticsActivityViewModel.insertDay(day)
            addRow(getLastDay(), day)

            Toast.makeText(this, "Day is saved", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Fields are invalid", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Checks the input values of the day
     */
    private fun checkValues() : Boolean {
        return etNewLength.text.isNotBlank() && etNewSun.text.isNotBlank() && etNewTemp.text.isNotBlank()
                && etNewWater.text.isNotBlank()
    }

    /**
     * Example of how the graph should look like
     */
    private fun plotGraph(plantWithDays: List<PlantWithDays>) {
        Log.i("PLANT: ", "${plant.name}")

        Log.i("higher then 0", "")


        var days = plantWithDays[plantPosition].days.size
        var day = 0.0
        var length = plantWithDays[plantPosition].plant.length

        val graph = findViewById<GraphView>(R.id.plantStatistics)
        series = LineGraphSeries<DataPoint>()

        graph.removeAllSeries()

        for (i in 0 until days) {
            day += 1
            length = plantWithDays[plantPosition].days[i].dayLength
            series.appendData(DataPoint(day, length), true, 1000)
        }

        graph.addSeries(series)

        // settings ui graph

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

        graph.setBackgroundColor(Color.rgb(225, 225, 225))
        graph.gridLabelRenderer.gridColor = Color.rgb(192, 192, 192)
        series.thickness = 5
        series.color = Color.rgb(93, 146, 84)
        series.isDrawDataPoints = true
        series.dataPointsRadius = 5.toFloat()
    }

}
