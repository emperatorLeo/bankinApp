package com.example.bankinapp.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.bankinapp.R
import com.example.bankinapp.ui.components.CameraPreview
import com.example.bankinapp.ui.navigation.Screen
import com.example.bankinapp.ui.states.PhotoStates
import com.example.bankinapp.ui.states.SignUpStates
import com.example.bankinapp.ui.theme.DarkBlue
import com.example.bankinapp.ui.theme.Dimen100dp
import com.example.bankinapp.ui.theme.Dimen10dp
import com.example.bankinapp.ui.theme.Dimen200dp
import com.example.bankinapp.ui.theme.Dimen20dp
import com.example.bankinapp.ui.theme.Dimen250dp
import com.example.bankinapp.ui.theme.Dimen40dp
import com.example.bankinapp.ui.theme.Dimen50dp
import com.example.bankinapp.ui.theme.Dimen5dp
import com.example.bankinapp.ui.theme.Font15sp
import com.example.bankinapp.ui.theme.Font20sp
import com.example.bankinapp.ui.theme.Font30sp
import com.example.bankinapp.ui.theme.GrayDisableText
import com.example.bankinapp.ui.theme.LightBlue
import com.example.bankinapp.ui.theme.MediumLightBlue
import com.example.bankinapp.ui.theme.WhiteText
import com.example.bankinapp.ui.viewmodel.MainViewModel
import com.example.bankinapp.util.TestTags.COMPLETE_SIGN_UP_BUTTON

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoScreen(
    cameraController: LifecycleCameraController,
    viewModel: MainViewModel,
    navController: NavController
) {
    val localContext = LocalContext.current

    val photoState = viewModel.photoState.collectAsState()

    var displayCamera by rememberSaveable {
        mutableStateOf(false)
    }

    val photoTaken = viewModel.photoTaken.observeAsState()

    val signUpStates by viewModel
        .signUpStates
        .collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .background(color = DarkBlue)
        ) {
            TopAppBar(title = { }, navigationIcon = {
                Icon(
                    modifier = Modifier
                        .padding(start = Dimen5dp)
                        .clickable {
                            navController.popBackStack()
                        },
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }, colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = MediumLightBlue))

            Text(
                modifier = Modifier
                    .padding(top = Dimen20dp)
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.take_photo_screen_title),
                color = WhiteText,
                fontSize = Font30sp,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .padding(top = Dimen10dp, bottom = 20.dp, start = Dimen10dp, end = Dimen10dp)
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.SemiBold,
                text = stringResource(id = R.string.take_photo_disclaimer),
                color = WhiteText,
                fontSize = Font15sp,
                textAlign = TextAlign.Center
            )

            if (photoState.value == PhotoStates.Taken) {
                Image(
                    photoTaken.value!!.asImageBitmap(),
                    contentDescription = "photo",
                    modifier = Modifier
                        .padding(top = Dimen100dp)
                        .size(Dimen200dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(10.dp))
                )
            } else {
                CameraPreview(
                    controller = cameraController,
                    Modifier
                        .padding(top = Dimen100dp)
                        .size(Dimen250dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            var buttonTop = Dimen50dp
            if (photoState.value == PhotoStates.Idle) {
                buttonTop = Dimen5dp
                IconButton(
                    modifier = Modifier
                        .padding(Dimen50dp)
                        .align(Alignment.CenterHorizontally),
                    onClick = {
                        takePhoto(
                            controller = cameraController,
                            localContext
                        ) {
                            viewModel.setPhoto(it)
                            displayCamera = !displayCamera
                        }
                    }) {
                    Icon(
                        imageVector = Icons.Default.PhotoCamera,
                        contentDescription = "Take a photo",
                        tint = WhiteText
                    )
                }
            }

            Button(
                modifier = Modifier
                    .padding(top = buttonTop, start = Dimen40dp, end = Dimen40dp, bottom = Dimen10dp)
                    .fillMaxWidth()
                    .height(Dimen50dp)
                    .testTag(COMPLETE_SIGN_UP_BUTTON),
                onClick = {
                    photoTaken.value?.let { viewModel.signUp(photo = it) }
                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightBlue,
                    contentColor = Color.White,
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = GrayDisableText
                ),
                enabled = !(photoState.value != PhotoStates.Taken || signUpStates == SignUpStates.Loading)
            ) {
                Text(
                    text = stringResource(id = R.string.sign_up_screen_title),
                    fontSize = Font20sp
                )
            }
        }

        when (signUpStates) {
            SignUpStates.Loading -> CircularProgressIndicator(
                modifier = Modifier
                    .padding(bottom = Dimen50dp)
                    .align(Alignment.BottomCenter)
                    .size(Dimen100dp, Dimen100dp),
                color = LightBlue,
                strokeWidth = Dimen10dp
            )

            SignUpStates.Success -> {
                navController.popBackStack()
                navController.navigate(route = Screen.Success.route)
            }

            SignUpStates.Failure -> {
                Toast.makeText(
                    localContext,
                    stringResource(id = R.string.sign_up_screen_failure_message),
                    Toast.LENGTH_LONG
                ).show()
            }

            SignUpStates.Idle -> {}
        }
    }
}

private fun takePhoto(
    controller: LifecycleCameraController,
    context: Context,
    onPhotoTaken: (Bitmap) -> Unit
) {
    controller.takePicture(
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)
                val matrix = Matrix().apply {
                    postRotate(image.imageInfo.rotationDegrees.toFloat())
                }

                val rotatedBitmap = Bitmap.createBitmap(
                    image.toBitmap(), 0, 0, image.width, image.height, matrix, true
                )
                onPhotoTaken(rotatedBitmap)
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                Log.e(
                    "Camera",
                    "there was an error trying to take your photo: ${exception.message}"
                )
            }
        })
}