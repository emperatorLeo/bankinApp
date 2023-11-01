package com.example.bankinapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankinapp.R
import com.example.bankinapp.data.db.entities.UserDataEntity
import com.example.bankinapp.ui.components.ActionButton
import com.example.bankinapp.ui.components.InputField
import com.example.bankinapp.ui.components.TextFieldType
import com.example.bankinapp.ui.navigation.Screen
import com.example.bankinapp.ui.states.UiStates
import com.example.bankinapp.ui.theme.BrightPurple
import com.example.bankinapp.ui.theme.Purple40
import com.example.bankinapp.ui.theme.PurpleGrey80
import com.example.bankinapp.ui.theme.White
import com.example.bankinapp.ui.viewmodel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun SignUpScreen(viewModel: MainViewModel, navController: NavController) {
    val uiState by viewModel
        .uiStates
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
                    .padding(top = 50.dp, bottom = 50.dp)
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
                modifier = Modifier.padding(top = 30.dp),
                PurpleGrey80,
                imageResource = R.drawable.ic_password
            ) { password = it }

            InputField(
                label = "INSERT YOUR NAME HERE",
                modifier = Modifier.padding(top = 30.dp),
                backgroundColor = PurpleGrey80,
                imageResource = R.drawable.ic_user
            ) { name = it }

            InputField(
                label = "INSERT YOUR LAST NAME HERE",
                modifier = Modifier.padding(top = 30.dp, bottom = 40.dp),
                backgroundColor = PurpleGrey80,
                imageResource = R.drawable.ic_user
            ) { lastName = it }

            ActionButton(text = "Sign up", color = BrightPurple) {

                viewModel.signUp(
                    email = email, userData = UserDataEntity(
                        name, lastName, password,
                        arrayListOf()
                    )
                )
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
                    LocalContext.current,
                    "There was a failure, please try again",
                    Toast.LENGTH_LONG
                ).show()
            }

            UiStates.EmptyFields -> {
                Toast.makeText(
                    LocalContext.current,
                    "Uno o mas campos estan vacios, todos los campos son obligatorios",
                    Toast.LENGTH_LONG
                ).show()
            }

            else -> {}
        }
    }

}
