package com.abdev.weatherdata.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abdev.weatherdata.R
import com.abdev.weatherdata.data.models.WeatherData
import com.abdev.weatherdata.utils.AppConstants
import java.text.DateFormatSymbols
import java.util.*

class WeatherDataAdapter(
    var modelList: ArrayList<WeatherData>,
    var context: Context,
    var typeParam: Int? = 0
) : RecyclerView.Adapter<WeatherDataAdapter.ViewHolder>() {

    var dateFormatSymbols: DateFormatSymbols = DateFormatSymbols.getInstance(Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_weather_layout, parent, false))

    override fun getItemCount() = modelList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weatherModel = modelList[position]
        val month = weatherModel.month ?: -1
        val year = weatherModel.year ?: -1
        val value = weatherModel.value ?: -1
        if (month != -1 && year != -1 && value != -1) {
            holder.dateValueView?.text = "${dateFormatSymbols.months[month - 1]} ,$year"
            holder.metricsValueView?.text = value.toString()
        } else {
            holder.dateValueView?.text = "N/A"
            holder.metricsValueView?.text = "N/A"
        }
        holder.metricImg?.setImageResource(
            when (typeParam) {
                AppConstants.METRIC_RAINFALL -> R.drawable.ic_rainfall
                AppConstants.METRIC_MXN_TEMP -> R.drawable.ic_rainfall
                else -> android.R.drawable.ic_input_add
            }
        )
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var metricImg: ImageView? = null
        var metricsValueView: TextView? = null
        var dateValueView: TextView? = null

        init {
            dateValueView = itemView.findViewById(R.id.dateValueView) as TextView
            metricsValueView = itemView.findViewById(R.id.metricsValueView) as TextView
            metricImg = itemView.findViewById(R.id.metricImg) as ImageView
        }
    }
}