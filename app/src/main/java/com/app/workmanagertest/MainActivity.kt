package com.app.workmanagertest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.work.*
import com.app.workmanagertest.ui.theme.WorkManagerTestTheme
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Constraints
         */
        /*val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .setRequiresStorageNotLow(true)
            .setRequiresDeviceIdle(true)
            .build()*/
        //val uploadDataConstraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        /**
         * periodicWorkRequest and OneTimeWork
         */
        //val periodicWorkRequest = PeriodicWorkRequestBuilder<MyBackgroundWorker>(24, TimeUnit.HOURS).build()
        val workRequest = OneTimeWorkRequestBuilder<MyBackgroundWorker>().build() //  .setConstraints(uploadDataConstraints)
        WorkManager.getInstance(this).enqueue(workRequest);

        /**
         * Result of work in LiveData
         */
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workRequest.id).observe(this, Observer {
            if(it.state.isFinished){
                Log.i("Work is Done::", it.outputData.getBoolean("is_success", false).toString())
            }
        })

        /**
         * TO cancel
         */
        //WorkManager.getInstance().cancelWorkById(periodicWorkRequest.id)


        setContent {
            WorkManagerTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorkManagerTestTheme {
        Greeting("Android")
    }
}