package com.abdev.weatherdata.fragments

import android.content.SharedPreferences
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
import com.abdev.weatherdata.R
import com.abdev.weatherdata.adapters.WeatherDataAdapter
import com.abdev.weatherdata.data.models.WeatherData
import com.abdev.weatherdata.data.viewmodel.WeatherViewModel


class WeatherListFragment : Fragment(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        Log.d("", "")
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
        recyclerView = rootView.findViewById(R.id.recyclerView)
        return rootView
    }

    override fun onResume() {
        super.onResume()
        //val sharedPreferences = context?.getSharedPreferences("APP", MODE_PRIVATE)
        //sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
        ViewModelProviders.of(this)
            .get(WeatherViewModel::class.java)
            .getListOfModels()
            .observe(this, observer)
    }

    override fun onStop() {
        super.onStop()
        //context?.getSharedPreferences("APP", MODE_PRIVATE)?.unregisterOnSharedPreferenceChangeListener(this)
    }

    private fun setAdapter(weatherDatList: List<WeatherData>) {
        val dataAdapter = WeatherDataAdapter(ArrayList(weatherDatList), context!!)
        recyclerView?.adapter = dataAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = WeatherListFragment()
    }
}
