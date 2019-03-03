package com.abdev.weatherdata.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.abdev.weatherdata.R
import com.abdev.weatherdata.data.models.CityData
import com.abdev.weatherdata.data.models.CityDataDao
import com.abdev.weatherdata.data.models.WeatherDao
import com.abdev.weatherdata.data.models.WeatherData
import java.util.concurrent.Executors


@Database(entities = [WeatherData::class, CityData::class], version = 1)
abstract class DataFactory : RoomDatabase() {

    companion object {
        private var INSTANCE: DataFactory? = null

        @Synchronized
        fun getInstance(context: Context): DataFactory {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, DataFactory::class.java, context.getString(R.string.db_name))
                    .allowMainThreadQueries()
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Executors.newSingleThreadScheduledExecutor().execute {
                                getInstance(context).cityDataDao.insertAll(ArrayList<CityData>().apply {
                                    add(CityData("UK", false))
                                    add(CityData("England", false))
                                    add(CityData("Scotland", true))
                                    add(CityData("Wales", false))
                                })
                            }
                        }
                    })
                    .build()
            }
            return INSTANCE as DataFactory
        }
    }

    abstract val weatherDao: WeatherDao

    abstract val cityDataDao: CityDataDao

}
