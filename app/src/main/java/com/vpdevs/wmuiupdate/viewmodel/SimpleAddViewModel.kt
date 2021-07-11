package com.vpdevs.wmuiupdate.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.vpdevs.wmuiupdate.utils.TAG_OUTPUT
import com.vpdevs.wmuiupdate.worker.SimpleAddWorker

class SimpleAddViewModel(application: Application) : AndroidViewModel(application) {

    private val workManager = WorkManager.getInstance(application)
    internal val workInfos: LiveData<List<WorkInfo>> =
        workManager.getWorkInfosByTagLiveData(TAG_OUTPUT)

    fun addNumbers() {
        val addWorker = OneTimeWorkRequestBuilder<SimpleAddWorker>()
        addWorker.addTag(TAG_OUTPUT)
        val addBuilder = addWorker.build()
        workManager.beginWith(addBuilder).enqueue()
    }

}