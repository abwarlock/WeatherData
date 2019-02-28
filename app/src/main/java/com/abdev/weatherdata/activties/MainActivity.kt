package com.abdev.weatherdata.activties

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.abdev.weatherdata.R
import com.abdev.weatherdata.data.DataFactory
import com.abdev.weatherdata.data.models.WeatherData

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var list = ArrayList<WeatherData>().apply {
            add(WeatherData())
            add(WeatherData())
            add(WeatherData())
        }

        DataFactory.getInstance(this).weatherDao.insertAll(list)
        val allList = DataFactory.getInstance(this).weatherDao.getAllList()
        Log.d("", "")
    }
}
