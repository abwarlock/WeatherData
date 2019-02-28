package com.abdev.weatherdata.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abdev.weatherdata.R
import com.abdev.weatherdata.data.models.WeatherDao
import com.abdev.weatherdata.data.models.WeatherData

@Database(entities = [WeatherData::class], version = 1)
abstract class DataFactory : RoomDatabase() {

    companion object {
        private var INSTANCE: DataFactory? = null

        @Synchronized
        fun getInstance(context: Context): DataFactory {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, DataFactory::class.java, context.getString(R.string.db_name))
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as DataFactory
        }
    }

    abstract val weatherDao: WeatherDao

}
