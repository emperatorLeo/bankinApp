package com.example.bankinapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.bankinapp.R
import com.example.bankinapp.ui.theme.Dimen100dp
import com.example.bankinapp.ui.theme.Dimen15dp
import com.example.bankinapp.ui.theme.Dimen20dp
import com.example.bankinapp.ui.theme.Dimen30dp
import com.example.bankinapp.ui.theme.Dimen50dp
import com.example.bankinapp.ui.theme.Dimen5dp
import com.example.bankinapp.ui.theme.Font20sp
import com.example.bankinapp.ui.theme.Font30sp
import com.example.bankinapp.ui.theme.GreenPositive
import com.example.bankinapp.ui.theme.Purple40
import com.example.bankinapp.ui.theme.Purple80
import com.example.bankinapp.ui.theme.RedNegative
import com.example.bankinapp.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetailScreen(viewModel: MainViewModel, navController: NavController) {
    val selectedMovement = viewModel.selectedMovement
    val color = if (selectedMovement.amount < 0) RedNegative else GreenPositive

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopAppBar(title = { }, navigationIcon = {
            Icon(
                modifier = Modifier
                    .padding(start = Dimen5dp)
                    .clickable {
                        navController.popBackStack()
                    },
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "",
                tint = Color.White
            )
        }, colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Purple80))
        Text(
            modifier = Modifier
                .padding(top = Dimen100dp, bottom = Dimen30dp)
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            text = stringResource(id = R.string.detail_screen_title),
            color = Purple40,
            fontSize = Font30sp,
            textAlign = TextAlign.Center
        )

        Image(
            modifier = Modifier
                .padding(top = Dimen20dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.bank_svg),
            contentDescription = "bank icon"
        )

        Row(Modifier.padding(top = Dimen50dp, start = Dimen15dp)) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.detail_screen_date),
                color = Purple40,
                fontSize = Font20sp,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                fontWeight = FontWeight.Normal,
                text = selectedMovement.date,
                color = Purple40,
                fontSize = Font20sp,
                textAlign = TextAlign.Center
            )
        }

        Row(Modifier.padding(top = Dimen50dp, start = Dimen15dp)) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.detail_screen_description),
                color = Purple40,
                fontSize = Font20sp,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                fontWeight = FontWeight.Normal,
                text = selectedMovement.description,
                color = Purple40,
                fontSize = Font20sp,
                textAlign = TextAlign.Center
            )
        }

        Row(Modifier.padding(top = Dimen50dp, start = Dimen15dp)) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.detail_screen_amount_label),
                color = Purple40,
                fontSize = Font20sp,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                fontWeight = FontWeight.Normal,
                text = selectedMovement.amount.toString(),
                color = color,
                fontSize = Font20sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
