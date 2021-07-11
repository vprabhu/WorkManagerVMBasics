package com.vpdevs.wmuiupdate.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.vpdevs.wmuiupdate.utils.TOTAL_COUNT
import com.vpdevs.wmuiupdate.utils.sleepForAddition

class  SimpleAddWorker(context: Context , workerParameters: WorkerParameters)
    : Worker(context , workerParameters) {

    override fun doWork(): Result {
        // log the thread info
        Log.d("SimpleAddWorker", "Thread : ${Thread.currentThread().name}")
        var total: Int = 0
        // for loop to add the numbers
        for (it in 1..10) {
            total += it
            // sleep for 2000 millis
            sleepForAddition()
            Log.d("SimpleAddWorker", "adding : $total by adding $it")
        }
        // return is result based on the total
        return if (total > 0) {
            Log.d("SimpleAddWorker", "Success and total is $total")
            val output = workDataOf(TOTAL_COUNT to total)
            Result.success(output)
        } else {
            Log.d("SimpleAddWorker", "Failure and total is $total")
            Result.failure()
        }
    }
}