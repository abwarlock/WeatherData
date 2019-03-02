package com.abdev.weatherdata.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.abdev.weatherdata.data.DataFactory
import com.abdev.weatherdata.networking.service.WeatherService
import com.abdev.weatherdata.networking.utils.NetworkUtils

class FetchDataWorker(var context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val weatherData = NetworkUtils.createService(WeatherService::class.java).getWeatherData("Rainfall", "England")
        return try {
            val execute = weatherData.execute()
            val dataList = execute.body()
            dataList?.let {
                DataFactory.getInstance(context).weatherDao.insertAll(it)
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}