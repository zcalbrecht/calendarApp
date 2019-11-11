package com.zcalbrecht.ageapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class MainActivity : AppCompatActivity() {

    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputYear: Button = findViewById(R.id.inputYear)
        inputYear.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd/MM/yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            inputYear.text = sdf.format(cal.time)

        }

        inputYear.setOnClickListener {
            DatePickerDialog(this@MainActivity, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }


    }

    fun onSubmit(view: View){
        try{
            val currentYear = Calendar.getInstance().get(Calendar.YEAR).toString().toInt()
            val pastYear = cal.get(Calendar.YEAR).toString().toInt()
            resultText.text = "Difference in years: " + (currentYear-pastYear)
        }
        catch(e : Exception){
            resultText.text = "That doesn't look like a valid year"
        }

    }


}
