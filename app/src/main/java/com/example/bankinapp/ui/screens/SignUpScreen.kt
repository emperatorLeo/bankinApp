package com.example.bankinapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankinapp.R
import com.example.bankinapp.ui.theme.BrightPurple
import com.example.bankinapp.ui.theme.Purple40
import com.example.bankinapp.ui.theme.PurpleGrey80
import com.example.bankinapp.ui.theme.White

@Preview
@Composable
fun SignUpScreen() {
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier,
            PurpleGrey80,
            imageResource = R.drawable.ic_email
        ){}
        InputField(
            label = "INSERT YOUR PASSWORD HERE",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.padding(top = 30.dp),
            PurpleGrey80,
            imageResource = R.drawable.ic_password
        ){}
        InputField(
            label = "INSERT YOUR NAME HERE",
            modifier = Modifier.padding(top = 30.dp),
            backgroundColor = PurpleGrey80,
            imageResource = R.drawable.ic_user
        ){}
        InputField(
            label = "INSERT YOUR LAST NAME HERE",
            modifier = Modifier.padding(top = 30.dp, bottom = 40.dp),
            backgroundColor = PurpleGrey80,
            imageResource = R.drawable.ic_user
        ){}

        ActionButton(text = "Sign up", color = BrightPurple) {
        }
    }
}
