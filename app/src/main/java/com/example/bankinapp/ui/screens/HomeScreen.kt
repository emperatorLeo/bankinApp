package com.example.bankinapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankinapp.ui.listitem.MovementItemList
import com.example.bankinapp.ui.navigation.Screen
import com.example.bankinapp.ui.theme.BrightPurple
import com.example.bankinapp.ui.theme.Purple40
import com.example.bankinapp.ui.theme.White
import com.example.bankinapp.ui.viewmodel.MainViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel, navController: NavController) {
    val email = viewModel.getUserData()?.first
    val userData = viewModel.getUserData()?.second!!
    val movements = viewModel.getMovementDetail()

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
            text = "Bienvenido \n\n${userData.name} ${userData.lastName}",
            color = Purple40,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .padding(top = 50.dp, bottom = 50.dp, start = 15.dp)
                .align(Alignment.Start),
            fontWeight = FontWeight.Bold,
            text = "Email: $email",
            color = Purple40,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .padding(top = 50.dp, bottom = 50.dp)
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Normal,
            text = "Movimientos",
            color = Purple40,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        if (movements!!.isEmpty()) {
            Text(
                text = " Aun no tienes movimientos en tu cuenta bancaria",
                modifier = Modifier.padding(top = 30.dp, start = 20.dp, end = 10.dp).align(Alignment.CenterHorizontally),
                color = BrightPurple,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        } else {

            LazyColumn(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
            ) {
                itemsIndexed(movements.toList()) { index, movement ->
                    MovementItemList(movements = movement) {
                        viewModel.selectMovement(index = index)
                        navController.navigate(Screen.TransactionDetail.route)
                    }
                }
            }
        }
    }
}
