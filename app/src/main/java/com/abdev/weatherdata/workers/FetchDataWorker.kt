package com.abdev.weatherdata.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.abdev.weatherdata.networking.service.WeatherService
import com.abdev.weatherdata.networking.utils.NetworkUtils

class FetchDataWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val weatherData = NetworkUtils.createService(WeatherService::class.java).getWeatherData("Rainfall", "England")
        return try {
            val execute = weatherData.execute()
            if (execute.isSuccessful) {
                Result.success()
            } else {
                Result.failure()
            }
        } catch (e: Exception) {
            Result.failure()
        }
    }
}