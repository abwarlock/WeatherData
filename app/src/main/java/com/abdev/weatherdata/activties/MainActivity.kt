package com.abdev.weatherdata.activties

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.abdev.weatherdata.R
import com.abdev.weatherdata.data.models.WeatherData
import com.abdev.weatherdata.networking.service.WeatherService
import com.abdev.weatherdata.networking.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NetworkUtils.createService(WeatherService::class.java).getWeatherData("Rainfall", "England").enqueue(object :
            Callback<ArrayList<WeatherData>> {
            override fun onFailure(call: Call<ArrayList<WeatherData>>, t: Throwable) {
                Log.d("", "")
            }

            override fun onResponse(call: Call<ArrayList<WeatherData>>, response: Response<ArrayList<WeatherData>>) {
                Log.d("", "")
            }
        })
    }
}
