package com.example.bankinapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bankinapp.R
import com.example.bankinapp.ui.navigation.Screen
import com.example.bankinapp.ui.theme.BrightPurple
import com.example.bankinapp.ui.theme.Purple40
import com.example.bankinapp.ui.theme.Purple80
import com.example.bankinapp.ui.theme.White
import com.example.bankinapp.ui.viewmodel.MainViewModel

@Composable
fun LoginScreen(viewModel: MainViewModel, navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
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
            Purple80,
            imageResource = R.drawable.ic_user
        )

        InputField(
            "Insert your password",
            modifier = Modifier,
            Purple80,
            imageResource = R.drawable.ic_password
        )

        ActionButton(text = "Continuar", color = Purple40) {
        }

        ActionButton(text = "Registrate", color = BrightPurple) {
            navController.popBackStack()
            navController.navigate(Screen.SignUp.route)
        }
    }
}
