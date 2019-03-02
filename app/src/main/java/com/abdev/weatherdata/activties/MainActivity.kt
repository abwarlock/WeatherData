package com.abdev.weatherdata.activties

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.abdev.weatherdata.R
import com.abdev.weatherdata.workers.FetchDataWorker

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val instance = WorkManager.getInstance()

        val workInfosByTag = instance.getWorkInfosByTagLiveData("A")
        workInfosByTag.observe(this, Observer<MutableList<WorkInfo>> { listOfWorkInfos ->
            if (listOfWorkInfos == null || listOfWorkInfos.isEmpty()) {
                return@Observer
            }
            val workInfo = listOfWorkInfos[0]

            val finished = workInfo.state.isFinished
            if (finished) {
                Log.d("", "")
            }
        })

        val build = OneTimeWorkRequest.Builder(FetchDataWorker::class.java).addTag("A").build()
        instance.enqueue(build)


    }
}
