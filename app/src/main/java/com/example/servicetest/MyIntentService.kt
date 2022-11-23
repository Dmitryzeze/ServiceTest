package com.example.servicetest

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

class MyIntentService : IntentService(NAME) {


    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startForeground(NOTIFICATION_ID,createNotification())
        log("onCreate")
    }

    override fun onDestroy() {

        super.onDestroy()
        log("onDestroy")
    }


    override fun onHandleIntent(p0: Intent?) {
        log("onHandleIntent")
        for (i in 0 until 100) {
            Thread.sleep(1000)
            log("Timer $i")
        }
    }

    private fun log(message: String) {
        Log.d("SERVICE_LOG", "MyForegroundService: $message")
    }

    private fun createNotificationChannel() {
        val notificationManager = getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Title")
            .setContentText("Text")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()

    }

    companion object {

        private const val CHANNEL_ID = "chanel_id"
        private const val NAME = "my_intent_service"
        private const val CHANNEL_NAME = "channel_name"
        private const val NOTIFICATION_ID = 1

        fun newIntent(context: Context): Intent {
            return Intent(context, MyIntentService::class.java)
        }
    }


}