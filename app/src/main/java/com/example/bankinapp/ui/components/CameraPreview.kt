package com.example.bankinapp.ui.components

import androidx.camera.core.CameraSelector
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CameraPreview(
    controller: LifecycleCameraController,
    modifier: Modifier = Modifier
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    AndroidView(factory = {
        PreviewView(it).apply {
            this.controller = controller
            controller.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            controller.bindToLifecycle(lifecycleOwner)
        }
    }, modifier = modifier)
}
