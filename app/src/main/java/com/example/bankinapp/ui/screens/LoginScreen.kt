package com.example.bankinapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.bankinapp.R
import com.example.bankinapp.ui.components.CustomInputField
import com.example.bankinapp.ui.components.TextFieldType
import com.example.bankinapp.ui.navigation.Screen
import com.example.bankinapp.ui.states.LoginUiStates
import com.example.bankinapp.ui.theme.BrightPurple
import com.example.bankinapp.ui.theme.Dimen100dp
import com.example.bankinapp.ui.theme.Dimen10dp
import com.example.bankinapp.ui.theme.Dimen200dp
import com.example.bankinapp.ui.theme.Dimen20dp
import com.example.bankinapp.ui.theme.Dimen40dp
import com.example.bankinapp.ui.theme.Dimen50dp
import com.example.bankinapp.ui.theme.Font20sp
import com.example.bankinapp.ui.theme.GrayDisableText
import com.example.bankinapp.ui.theme.Purple40
import com.example.bankinapp.ui.theme.Purple80
import com.example.bankinapp.ui.theme.White
import com.example.bankinapp.ui.viewmodel.MainViewModel
import com.example.bankinapp.util.TestTags.SIGN_UP_BUTTON

@Composable
fun LoginScreen(viewModel: MainViewModel, navController: NavController) {
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }

    val isEmailValid = remember {
        mutableStateOf(false)
    }

    val uiState by viewModel.loginUiStates.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .background(White)
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = Dimen50dp, bottom = Dimen10dp)
                    .width(Dimen200dp)
                    .height(Dimen200dp),
                painter = painterResource(id = R.drawable.bank_svg),
                contentDescription = "welcome icon"
            )

            CustomInputField(
                stringResource(id = R.string.login_screen_email_hint),
                textFieldType = TextFieldType.EMAIL,
                modifier = Modifier.padding(bottom = Dimen20dp),
                backgroundColor = Purple80,
                imageResource = R.drawable.ic_user,
                isValidEmail = { isEmailValid.value = it }
            ) { email.value = it }

            CustomInputField(
                stringResource(id = R.string.login_screen_password_hint),
                textFieldType = TextFieldType.PASSWORD,
                modifier = Modifier.padding(bottom = Dimen40dp),
                Purple80,
                imageResource = R.drawable.ic_password
            ) { password.value = it }

            Button(
                modifier = Modifier
                    .padding(top = Dimen10dp, start = Dimen40dp, end = Dimen40dp)
                    .fillMaxWidth()
                    .height(Dimen50dp),
                onClick = {
                    viewModel.login(email.value.trim(), password.value.trim())
                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple40,
                    contentColor = Color.White,
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = GrayDisableText
                ),
                enabled = isEmailValid.value && password.value.isNotEmpty()
            ) {
                Text(
                    text = stringResource(id = R.string.login_screen_sign_in_text),
                    fontSize = Font20sp
                )
            }

            Button(
                modifier = Modifier
                    .padding(top = Dimen10dp, start = Dimen40dp, end = Dimen40dp)
                    .fillMaxWidth()
                    .height(Dimen50dp)
                    .testTag(SIGN_UP_BUTTON),
                onClick = {
                    navController.navigate(Screen.SignUp.route)
                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = BrightPurple,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(id = R.string.login_screen_sign_up_text),
                    fontSize = Font20sp
                )
            }
        }

        when (uiState) {
            LoginUiStates.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(Dimen100dp, Dimen100dp),
                    color = BrightPurple,
                    strokeWidth = Dimen10dp
                )
            }

            LoginUiStates.WrongCredentials -> {
                Toast.makeText(
                    LocalContext.current,
                    stringResource(id = R.string.login_screen_error_text),
                    Toast.LENGTH_LONG
                ).show()
            }

            LoginUiStates.Success -> {
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            }

            LoginUiStates.Failure -> {
                Toast.makeText(
                    LocalContext.current,
                    stringResource(id = R.string.login_screen_failure_text),
                    Toast.LENGTH_LONG
                ).show()
            }

            LoginUiStates.Idle -> {}
        }
    }
}
