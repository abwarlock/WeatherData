package com.abdev.weatherdata.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.abdev.weatherdata.R
import com.abdev.weatherdata.adapters.WeatherDataAdapter
import com.abdev.weatherdata.data.models.WeatherData
import com.abdev.weatherdata.data.viewmodel.WeatherViewModel
import com.abdev.weatherdata.utils.AppConstants
import com.abdev.weatherdata.workers.FetchDataWorker
import com.abdev.weatherdata.workers.WEATHER_TYPE


class WeatherListFragment : Fragment() {

    val TYPE_ARG = "TYPE_KEY"
    private var typeParam: Int? = null

    companion object {
        @JvmStatic
        fun newInstance(@AppConstants type: Int) = WeatherListFragment().apply {
            arguments = Bundle().apply {
                putInt(TYPE_ARG, type)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            typeParam = it.getInt(TYPE_ARG)
        }
    }

    private val observer = Observer<List<WeatherData>> { weatherDatList ->
        weatherDatList?.let {
            setAdapter(weatherDatList)
        }
    }

    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_weather_list, container, false)
        swipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout?.isEnabled = false
        recyclerView = rootView.findViewById(R.id.recyclerView)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState == null) {
            fetchData()
        }
    }


    private fun fetchData() {
        val instance = WorkManager.getInstance()
        val workInfoByTag = instance.getWorkInfosByTagLiveData(typeParam.toString())
        workInfoByTag.observe(this, Observer<MutableList<WorkInfo>> { listOfWorkInfo ->
            if (listOfWorkInfo == null || listOfWorkInfo.isEmpty()) {
                return@Observer
            }
            val workInfo = listOfWorkInfo[0]

            val finished = workInfo.state.isFinished
            if (finished) {
                Log.d("", "")
            }
        })
        val build = OneTimeWorkRequest.Builder(FetchDataWorker::class.java)
            .setInputData(Data.Builder().apply {
                typeParam?.let { putInt(WEATHER_TYPE, it) }
            }.build())
            .addTag(typeParam.toString())
            .build()

        instance.enqueue(build)
    }


    override fun onResume() {
        super.onResume()
        ViewModelProviders.of(this)
            .get(WeatherViewModel::class.java)
            .getListOfModels(typeParam!!)
            .observe(this, observer)
    }

    private fun setAdapter(weatherDatList: List<WeatherData>) {
        val dataAdapter = WeatherDataAdapter(ArrayList(weatherDatList), context!!, typeParam)
        recyclerView?.adapter = dataAdapter
    }



}
