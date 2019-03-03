package com.abdev.weatherdata.data.viewmodel

import androidx.lifecycle.ViewModel
import com.abdev.weatherdata.MyApplication
import com.abdev.weatherdata.data.DataFactory

class WeatherViewModel : ViewModel() {

    fun getListOfModels(typeParam: Int) =
        DataFactory.getInstance(MyApplication.getContext()).weatherDao.getLiveDataList(typeParam)

    fun getListOfPageModels() = DataFactory.getInstance(MyApplication.getContext()).weatherDao.getLiveDataList()
}