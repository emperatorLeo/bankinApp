package com.example.bankinapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bankinapp.R
import com.example.bankinapp.ui.components.ActionButton
import com.example.bankinapp.ui.components.InputField
import com.example.bankinapp.ui.components.TextFieldType
import com.example.bankinapp.ui.navigation.Screen
import com.example.bankinapp.ui.states.UiStates
import com.example.bankinapp.ui.theme.BrightPurple
import com.example.bankinapp.ui.theme.Purple40
import com.example.bankinapp.ui.theme.Purple80
import com.example.bankinapp.ui.theme.White
import com.example.bankinapp.ui.viewmodel.MainViewModel

@Composable
fun LoginScreen(viewModel: MainViewModel, navController: NavController) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }

    val uiState by viewModel
        .uiStates
        .observeAsState()

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
                    .padding(top = 100.dp, bottom = 10.dp)
                    .width(300.dp)
                    .height(300.dp),
                painter = painterResource(id = R.drawable.bank_svg),
                contentDescription = "welcome icon"
            )

            InputField(
                "Insert your email",
                modifier = Modifier,
                backgroundColor = Purple80,
                imageResource = R.drawable.ic_user
            ) { email = it }

            InputField(
                "Insert your password",
                textFieldType = TextFieldType.PASSWORD,
                modifier = Modifier,
                Purple80,
                imageResource = R.drawable.ic_password
            ) { password = it }

            ActionButton(text = "Continuar", color = Purple40) {
                viewModel.login(email, password)
            }

            ActionButton(text = "Registrate", color = BrightPurple) {
                navController.popBackStack()
                navController.navigate(Screen.SignUp.route)
            }
        }

        if (uiState == UiStates.Loading)
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(100.dp, 100.dp),
                color = BrightPurple,
                strokeWidth = 10.dp
            )

        if (uiState == UiStates.WrongCredentials) {
            Toast.makeText(
                LocalContext.current,
                "Your EMAIL or PASSWORD is wrong, please try again",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
