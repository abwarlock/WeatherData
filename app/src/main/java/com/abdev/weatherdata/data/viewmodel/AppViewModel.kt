package com.abdev.weatherdata.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.abdev.weatherdata.MyApplication
import com.abdev.weatherdata.data.DataFactory
import com.abdev.weatherdata.data.models.WeatherData

class AppViewModel : ViewModel() {

    fun getListOfModels(typeParam: Int): LiveData<List<WeatherData>> {
        val dataFactory = DataFactory.getInstance(MyApplication.getContext())
        return dataFactory.weatherDao.getLiveDataListWithSelectedCity(typeParam)
    }

    fun getSelectedCity() =
        DataFactory.getInstance(MyApplication.getContext()).cityDataDao.getSelectedCityLiveData(true)

    fun getListOfPageModels() = DataFactory.getInstance(MyApplication.getContext()).weatherDao.getLiveDataList()
}