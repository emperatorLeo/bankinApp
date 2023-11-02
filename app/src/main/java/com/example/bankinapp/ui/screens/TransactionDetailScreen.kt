package com.example.bankinapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankinapp.ui.theme.Purple40
import com.example.bankinapp.ui.viewmodel.MainViewModel

@Composable
fun TransactionDetailScreen(viewModel: MainViewModel) {
    val selectedMovement = viewModel.selectedMovement

    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Text(
            modifier = Modifier
                .padding(top = 150.dp, bottom = 50.dp)
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            text = "Transaction Details",
            color = Purple40,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .padding(top = 50.dp, start = 15.dp)
                .align(Alignment.Start),
            fontWeight = FontWeight.Bold,
            text = "Date: ${selectedMovement.date}",
            color = Purple40,
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .padding(top = 50.dp, start = 15.dp)
                .align(Alignment.Start),
            fontWeight = FontWeight.Bold,
            text = "Description: ${selectedMovement.description}",
            color = Purple40,
            fontSize = 20.sp,
            textAlign = TextAlign.Start
        )

        Text(
            modifier = Modifier
                .padding(top = 50.dp, start = 15.dp)
                .align(Alignment.Start),
            fontWeight = FontWeight.Bold,
            text = "Amount: $ ${selectedMovement.amount}",
            color = Purple40,
            fontSize = 20.sp,
            textAlign = TextAlign.Start
        )
    }
}