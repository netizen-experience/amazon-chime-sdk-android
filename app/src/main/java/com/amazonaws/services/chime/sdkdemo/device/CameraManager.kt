package com.amazonaws.services.chime.sdkdemo.device

import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import com.amazonaws.services.chime.sdk.meetings.audiovideo.video.capture.CameraCaptureSource
import com.amazonaws.services.chime.sdk.meetings.audiovideo.video.capture.CaptureSourceObserver
import com.amazonaws.services.chime.sdkdemo.service.CameraService

class CameraManager(
    private val cameraCaptureSource: CameraCaptureSource,
    private val context: Context
) {
    val videoSource = cameraCaptureSource
    var cameraConnectionService: ServiceConnection? = null

    fun start() {
        cameraCaptureSource.start()
    }

    fun stop(isBound: Boolean = false) {
        context.stopService(Intent(context, CameraService::class.java))
        cameraCaptureSource.stop()
        cameraConnectionService?.let { if (isBound) context.unbindService(it) }
    }

    fun addObserver(observer: CaptureSourceObserver) =
        cameraCaptureSource.addCaptureSourceObserver(observer)
}
