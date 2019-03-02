package com.abdev.weatherdata.data.viewmodel

import androidx.lifecycle.ViewModel
import com.abdev.weatherdata.MyApplication
import com.abdev.weatherdata.data.DataFactory

class WeatherViewModel : ViewModel() {

    fun getListOfModels() = DataFactory.getInstance(MyApplication.getContext()).weatherDao.getLiveDataList()

    fun getListOfPageModels() = DataFactory.getInstance(MyApplication.getContext()).weatherDao.getLiveDataList()
}