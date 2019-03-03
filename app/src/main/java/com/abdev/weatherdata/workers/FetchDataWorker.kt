package com.abdev.weatherdata.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.abdev.weatherdata.data.DataFactory
import com.abdev.weatherdata.networking.service.WeatherService
import com.abdev.weatherdata.networking.utils.NetworkUtils
import com.abdev.weatherdata.utils.AppConstants

const val WEATHER_TYPE = "WEATHER_TYPE"

class FetchDataWorker(var context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val type = inputData.getInt(WEATHER_TYPE, -1)
        val cityName = getCityName()
        return if (type != -1) {
            val weatherData =
                NetworkUtils.createService(WeatherService::class.java).getWeatherData(getMetricValue(type), cityName)
            try {
                val execute = weatherData.execute()
                val dataList = execute.body()
                dataList?.let { list ->
                    list.forEach { model ->
                        model.metricType = type
                        model.cityName = cityName
                    }
                    DataFactory.getInstance(context).weatherDao.insertAll(list)
                }
                Result.success()
            } catch (e: Exception) {
                Result.failure()
            }
        } else {
            Result.failure()
        }
    }

    private fun getMetricValue(metricType: Int): String {
        return when (metricType) {
            AppConstants.METRIC_MXN_TEMP -> "Tmax"
            AppConstants.METRIC_MIN_TEMP -> "Tmin"
            else -> "Rainfall"
        }
    }

    private fun getCityName(): String {
        val cityData = DataFactory.getInstance(context).cityDataDao.getSelectedCity(true)
        return cityData.cityName
    }
}