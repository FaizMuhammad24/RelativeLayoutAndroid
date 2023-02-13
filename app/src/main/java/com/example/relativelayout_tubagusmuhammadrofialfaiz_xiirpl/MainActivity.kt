package com.example.relativelayout_tubagusmuhammadrofialfaiz_xiirpl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSpinnerDay()
        setSpinnerTime()
    }

    private fun setSpinnerDay(){
        val spinnerDay:Spinner=findViewById(R.id.spinnerDate)

        ArrayAdapter.createFromResource(
            this,
            R.array.dayArray,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item
        ).also{
            it.setDropDownViewResource (com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerDay.adapter=it
        }
    }

    private fun setSpinnerTime(){
        val spinnerTime:Spinner=findViewById(R.id.spinnerTime)

        ArrayAdapter.createFromResource(
            this,
            R.array.timeArray,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item
        ).also {
            it.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerTime.adapter=it
        }
    }
}