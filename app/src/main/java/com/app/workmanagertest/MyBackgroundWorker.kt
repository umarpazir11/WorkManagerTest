package com.app.workmanagertest

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf


class MyBackgroundWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams)  {
    override fun doWork(): Result {
        val isSuccess =  uploadData()
        val outputData = workDataOf("is_success" to isSuccess)
        return Result.success(outputData)
    }

    private fun uploadData(): Boolean {
        Log.i("Upload", "Data")
        return true
    }
}