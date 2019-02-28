package com.abdev.weatherdata.networking.service

import com.abdev.weatherdata.data.models.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("/interview-question-data/metoffice/{metric}-{location}.json")
    fun getWeatherData(
        @Path("metric") metric: String,
        @Path("location") location: String
    ): Call<ArrayList<WeatherData>>
}