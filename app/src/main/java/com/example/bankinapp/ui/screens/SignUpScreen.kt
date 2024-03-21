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
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.bankinapp.model.UserInformation
import com.example.bankinapp.ui.components.ActionButton
import com.example.bankinapp.ui.components.CameraPreview
import com.example.bankinapp.ui.components.CustomInputField
import com.example.bankinapp.ui.components.TextFieldType.EMAIL
import com.example.bankinapp.ui.components.TextFieldType.PASSWORD
import com.example.bankinapp.ui.navigation.Screen
import com.example.bankinapp.ui.states.SignUpStates
import com.example.bankinapp.ui.theme.BrightPurple
import com.example.bankinapp.ui.theme.Dimen100dp
import com.example.bankinapp.ui.theme.Dimen10dp
import com.example.bankinapp.ui.theme.Dimen120dp
import com.example.bankinapp.ui.theme.Dimen20dp
import com.example.bankinapp.ui.theme.Dimen40dp
import com.example.bankinapp.ui.theme.Dimen50dp
import com.example.bankinapp.ui.theme.Dimen5dp
import com.example.bankinapp.ui.theme.Font15sp
import com.example.bankinapp.ui.theme.Font20sp
import com.example.bankinapp.ui.theme.Font30sp
import com.example.bankinapp.ui.theme.GrayDisableText
import com.example.bankinapp.ui.theme.Pink40
import com.example.bankinapp.ui.theme.Purple40
import com.example.bankinapp.ui.theme.Purple80
import com.example.bankinapp.ui.theme.PurpleGrey80
import com.example.bankinapp.ui.theme.White
import com.example.bankinapp.ui.viewmodel.MainViewModel
import com.example.bankinapp.util.TestTags.COMPLETE_SIGN_UP_BUTTON
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    viewModel: MainViewModel,
    cameraController: LifecycleCameraController,
    navController: NavController
) {
    val uiState by viewModel
        .signUpStates
        .collectAsState()

    val photoTaken by viewModel
        .photoTaken
        .observeAsState()

    val userInformation = remember {
        mutableStateOf(UserInformation.userEmpty)
    }

    var displayCamera by rememberSaveable {
        mutableStateOf(false)
    }

    val inputTextState = remember {
        mutableStateOf(InputTextState.idle)
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
            TopAppBar(title = { }, navigationIcon = {
                Icon(
                    modifier = Modifier
                        .padding(start = Dimen5dp)
                        .clickable {
                            navController.popBackStack()
                        },
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }, colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Purple80))

            Text(
                modifier = Modifier
                    .padding(top = Dimen20dp)
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.sign_up_screen_title),
                color = Purple40,
                fontSize = Font30sp,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .padding(top = Dimen10dp, bottom = 20.dp, start = Dimen10dp, end = Dimen10dp)
                    .align(Alignment.Start),
                fontWeight = FontWeight.SemiBold,
                text = stringResource(id = R.string.sign_up_screen_disclaimer),
                color = Pink40,
                fontSize = Font15sp,
                textAlign = TextAlign.Center
            )

            CustomInputField(
                label = stringResource(id = R.string.sign_up_screen_email_label),
                modifier = Modifier.padding(top = Dimen20dp, bottom = Dimen10dp),
                textFieldType = EMAIL,
                backgroundColor = PurpleGrey80,
                imageResource = R.drawable.ic_email,
                isValidEmail = {
                    inputTextState.value = inputTextState.value.copy(isEmailOk = it)
                }
            ) { userInformation.value = userInformation.value.copy(email = it) }

            CustomInputField(
                label = stringResource(id = R.string.sign_up_screen_password_label),
                textFieldType = PASSWORD,
                modifier = Modifier.padding(bottom = Dimen10dp),
                PurpleGrey80,
                imageResource = R.drawable.ic_password,
            ) {
                inputTextState.value = inputTextState.value.copy(isPasswordOk = it.isNotEmpty())
                userInformation.value = userInformation.value.copy(password = it)
            }

            CustomInputField(
                label = stringResource(id = R.string.sign_up_screen_name_label),
                modifier = Modifier.padding(bottom = Dimen10dp),
                backgroundColor = PurpleGrey80,
                imageResource = R.drawable.ic_user,
                minLengthAllowed = 3,
                reachMinAllowed = {
                    inputTextState.value = inputTextState.value.copy(isNameOk = it)
                }
            ) { userInformation.value = userInformation.value.copy(name = it) }

            CustomInputField(
                label = stringResource(id = R.string.sign_up_screen_surname_label),
                modifier = Modifier.padding(bottom = Dimen10dp),
                backgroundColor = PurpleGrey80,
                imageResource = R.drawable.ic_user,
                minLengthAllowed = 2,
                reachMinAllowed = {
                    inputTextState.value = inputTextState.value.copy(isSurnameOk = it)
                }
            ) { userInformation.value = userInformation.value.copy(surname = it) }

            if (photoTaken != null) {
                Image(
                    photoTaken!!.asImageBitmap(),
                    contentDescription = "photo",
                    modifier = Modifier
                        .width(Dimen100dp)
                        .height(Dimen120dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(10.dp))
                )
            }

            ActionButton(
                text = stringResource(id = R.string.sign_up_screen_photo_label),
                color = Purple40
            ) {
                displayCamera = !displayCamera
            }
            
            Button(
                modifier = Modifier
                    .padding(top = Dimen10dp, start = Dimen40dp, end = Dimen40dp)
                    .fillMaxWidth()
                    .height(Dimen50dp)
                    .testTag(COMPLETE_SIGN_UP_BUTTON),
                onClick = {
                    viewModel.signUp(userInformation.value)
                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor =  BrightPurple,
                    contentColor = Color.White,
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = GrayDisableText
                ),
                enabled = inputTextState.value.areAllOk()
            ) {
                Text(
                    text = stringResource(id = R.string.sign_up_screen_title),
                    fontSize = Font20sp
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
            SignUpStates.Loading -> CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(Dimen100dp, Dimen100dp),
                color = BrightPurple,
                strokeWidth = Dimen10dp
            )

            SignUpStates.Success -> {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center)
                        .background(BrightPurple)
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_up_screen_success_message),
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

            SignUpStates.Failure -> {
                Toast.makeText(
                    localContext,
                    stringResource(id = R.string.sign_up_screen_failure_message),
                    Toast.LENGTH_LONG
                ).show()
            }

            SignUpStates.EmptyFields -> {
                Toast.makeText(
                    localContext,
                    stringResource(id = R.string.sign_up_screen_empty_fields_message),
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
