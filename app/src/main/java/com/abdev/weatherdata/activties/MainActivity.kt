package com.abdev.weatherdata.activties

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.viewpager.widget.ViewPager
import com.abdev.weatherdata.R
import com.abdev.weatherdata.adapters.TabPageAdapter
import com.abdev.weatherdata.data.DataFactory
import com.abdev.weatherdata.data.models.CityData
import com.abdev.weatherdata.fragments.CityDataListDialogFragment
import com.abdev.weatherdata.fragments.WeatherListFragment
import com.abdev.weatherdata.utils.AppConstants
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(), CityDataListDialogFragment.Listener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val adapter = TabPageAdapter(supportFragmentManager)
        adapter.addFragment(WeatherListFragment.newInstance(AppConstants.METRIC_MXN_TEMP), getString(R.string.MAX_TEMP))
        adapter.addFragment(WeatherListFragment.newInstance(AppConstants.METRIC_MIN_TEMP), getString(R.string.MIN_TEMP))
        adapter.addFragment(WeatherListFragment.newInstance(AppConstants.METRIC_RAINFALL), getString(R.string.RAINFALL))
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        val city = DataFactory.getInstance(this).cityDataDao.getSelectedCity(true)
        title = "${city?.cityName} ${getString(R.string.WEATHER)} "
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        var drawable = menu.findItem(R.id.action_change_city).icon
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.white));
        menu.findItem(R.id.action_change_city).icon = drawable;
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.action_change_city -> {
            CityDataListDialogFragment.newInstance().show(supportFragmentManager, "TAG")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onCityDataClicked(cityData: CityData) {
        title = "${cityData.cityName} ${getString(R.string.WEATHER)} "
        DataFactory.getInstance(this).cityDataDao.updateData(cityData.cityName)
    }
}
