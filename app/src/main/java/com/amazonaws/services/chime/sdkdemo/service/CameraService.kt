package com.amazonaws.services.chime.sdkdemo.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.amazonaws.services.chime.sdkdemo.R
import com.amazonaws.services.chime.sdkdemo.utils.isOSVersionAtLeast

class CameraService : Service() {
    private lateinit var notificationManager: NotificationManager
    private val CHANNEL_ID = "CameraServiceChannel"
    private val CHANNEL_NAME = "Camera Service Channel"
    private val NOTIFICATION_ID = 1

    private val binder = CameraBinder()

    inner class CameraBinder : Binder() {
        fun getService(): CameraService = this@CameraService
    }

    override fun onCreate() {
        super.onCreate()
        notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (isOSVersionAtLeast(Build.VERSION_CODES.O)) {
            val channel =
                NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        if (isOSVersionAtLeast(Build.VERSION_CODES.R)) {
            startForeground(
                NOTIFICATION_ID,
                NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(getString(R.string.camera_notification_tile))
                    .setContentText(getText(R.string.camera_notification_text))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build(),
                ServiceInfo.FOREGROUND_SERVICE_TYPE_CAMERA
            )
        } else {
            startForeground(
                NOTIFICATION_ID,
                NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(getString(R.string.camera_notification_tile))
                    .setContentText(getText(R.string.camera_notification_text))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build()
            )
        }

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder = binder
}
