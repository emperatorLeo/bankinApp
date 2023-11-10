package com.example.bankinapp.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import android.widget.Toast
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.bankinapp.R
import com.example.bankinapp.data.db.entities.UserDataEntity
import com.example.bankinapp.ui.components.ActionButton
import com.example.bankinapp.ui.components.CameraPreview
import com.example.bankinapp.ui.components.InputField
import com.example.bankinapp.ui.components.TextFieldType
import com.example.bankinapp.ui.navigation.Screen
import com.example.bankinapp.ui.states.UiStates
import com.example.bankinapp.ui.theme.BrightPurple
import com.example.bankinapp.ui.theme.Purple40
import com.example.bankinapp.ui.theme.PurpleGrey80
import com.example.bankinapp.ui.theme.White
import com.example.bankinapp.ui.viewmodel.MainViewModel
import com.example.bankinapp.util.Tags.COMPLETE_SIGN_UP_BUTTON
import kotlinx.coroutines.delay

@Composable
fun SignUpScreen(
    viewModel: MainViewModel,
    cameraController: LifecycleCameraController,
    navController: NavController
) {
    val uiState by viewModel
        .uiStates
        .observeAsState()

    val photoTaken by viewModel
        .photoTaken
        .observeAsState()

    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var name by rememberSaveable {
        mutableStateOf("")
    }
    var lastName by rememberSaveable {
        mutableStateOf("")
    }

    var displayCamera by rememberSaveable {
        mutableStateOf(false)
    }

    val localContext = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(White)

        ) {
            Text(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp)
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
                text = "SIGN UP",
                color = Purple40,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
            InputField(
                label = "INSERT YOUR EMAIL HERE",
                modifier = Modifier,
                backgroundColor = PurpleGrey80,
                imageResource = R.drawable.ic_email
            ) { email = it }

            InputField(
                label = "INSERT YOUR PASSWORD HERE",
                textFieldType = TextFieldType.PASSWORD,
                modifier = Modifier,
                PurpleGrey80,
                imageResource = R.drawable.ic_password
            ) { password = it }

            InputField(
                label = "INSERT YOUR NAME HERE",
                modifier = Modifier,
                backgroundColor = PurpleGrey80,
                imageResource = R.drawable.ic_user
            ) { name = it }

            InputField(
                label = "INSERT YOUR LAST NAME HERE",
                modifier = Modifier.padding(bottom = 10.dp),
                backgroundColor = PurpleGrey80,
                imageResource = R.drawable.ic_user
            ) { lastName = it }

            if (photoTaken != null) {
                Image(
                    photoTaken!!.asImageBitmap(),
                    contentDescription = "photo",
                    modifier = Modifier
                        .width(100.dp)
                        .height(120.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(10.dp))
                )
            }

            ActionButton(text = "Take a photo of you", color = Purple40) {
                displayCamera = !displayCamera
            }

            ActionButton(modifier = Modifier.testTag(COMPLETE_SIGN_UP_BUTTON),text = "Sign up", color = BrightPurple) {
                viewModel.signUp(
                    email = email,
                    userData = UserDataEntity(
                        name,
                        lastName,
                        password,
                        arrayListOf()
                    )
                )
            }
        }

        if (displayCamera) {
            CameraPreview(controller = cameraController, Modifier.fillMaxSize())
            Row(
                Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                IconButton(onClick = {
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
                        tint = White
                    )
                }

            }
        }
        when (uiState) {
            UiStates.Loading -> CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(100.dp, 100.dp),
                color = BrightPurple,
                strokeWidth = 10.dp
            )

            UiStates.Success -> {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center)
                        .background(BrightPurple)
                ) {
                    Text(
                        text = "Tu cuenta ha sido creada exitosamente!, seras redirigido a la pantalla de login para que ingreses con tus credenciales",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    LaunchedEffect(Unit) {
                        delay(2500)
                        navController.popBackStack()
                        navController.navigate(Screen.Login.route)
                    }
                }
            }

            UiStates.Failure -> {
                Toast.makeText(
                    localContext,
                    "There was a failure, please try again",
                    Toast.LENGTH_LONG
                ).show()
            }

            UiStates.EmptyFields -> {
                Toast.makeText(
                    localContext,
                    "Uno o mas campos estan vacios, todos los campos son obligatorios",
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {}
        }
    }
}

private fun takePhoto(
    controller: LifecycleCameraController,
    context: Context,
    onPhotoTaken: (Bitmap) -> Unit
) {
    controller.takePicture(ContextCompat.getMainExecutor(context),
        object : OnImageCapturedCallback() {
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
