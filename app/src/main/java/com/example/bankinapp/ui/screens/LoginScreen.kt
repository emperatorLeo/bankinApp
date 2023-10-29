package com.example.bankinapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankinapp.R
import com.example.bankinapp.ui.theme.BrightPurple
import com.example.bankinapp.ui.theme.Pink40
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

        InputField("Insert your email", modifier = Modifier, imageResource = R.drawable.ic_user)

        InputField(
            "Insert your password",
            modifier = Modifier,
            imageResource = R.drawable.ic_password
        )

        ActionButton(text = "Continuar", color = Purple40) {
        }

        ActionButton(text = "Registrate", color = BrightPurple) {
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(label: String, modifier: Modifier, imageResource: Int) {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    Row(
        modifier
            .padding(top = 5.dp, start = 20.dp)
            .height(100.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "editext icon",
            modifier = modifier.align(Alignment.CenterVertically)
        )

        TextField(
            modifier = Modifier
                .padding(start = 10.dp)
                .align(Alignment.CenterVertically),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Pink40,
                containerColor = Purple80,
                cursorColor = Pink40,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            value = text,
            label = { Text(text = label, color = Pink40) },
            onValueChange = {
                text = it
            }
        )
    }
}

@Composable
fun ActionButton(modifier: Modifier = Modifier, text: String, color: Color, action: () -> Unit) {
    Box(
        modifier = modifier
            .padding(top = 10.dp, start = 40.dp, end = 40.dp)
            .background(
                color = color,
                shape = CircleShape
            )
            .fillMaxWidth()
            .height(50.dp)
            .clickable { action.invoke() }
    ) {
        Text(
            text = text,
            modifier
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = White
        )
    }
}
