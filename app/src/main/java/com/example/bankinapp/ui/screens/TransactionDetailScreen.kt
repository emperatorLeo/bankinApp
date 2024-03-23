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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.bankinapp.R
import com.example.bankinapp.ui.theme.DarkBlue
import com.example.bankinapp.ui.theme.Dimen100dp
import com.example.bankinapp.ui.theme.Dimen15dp
import com.example.bankinapp.ui.theme.Dimen20dp
import com.example.bankinapp.ui.theme.Dimen30dp
import com.example.bankinapp.ui.theme.Dimen50dp
import com.example.bankinapp.ui.theme.Dimen5dp
import com.example.bankinapp.ui.theme.Font20sp
import com.example.bankinapp.ui.theme.Font30sp
import com.example.bankinapp.ui.theme.WhiteText
import com.example.bankinapp.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetailScreen(viewModel: MainViewModel, navController: NavController) {
    val selectedMovement = viewModel.selectedMovement

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
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
                tint = WhiteText
            )
        }, colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = DarkBlue))
        Text(
            modifier = Modifier
                .padding(top = Dimen100dp, bottom = Dimen30dp)
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            text = stringResource(id = R.string.detail_screen_title),
            color = WhiteText,
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
                color = WhiteText,
                fontSize = Font20sp,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                fontWeight = FontWeight.Normal,
                text = selectedMovement.date,
                color = WhiteText,
                fontSize = Font20sp,
                textAlign = TextAlign.Center
            )
        }

        Column(Modifier.padding(top = Dimen50dp, start = Dimen15dp)) {
            Text(
                modifier = Modifier
                    .align(Alignment.Start),
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.detail_screen_description),
                color = WhiteText,
                fontSize = Font20sp,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .padding(Dimen5dp)
                    .align(Alignment.Start),
                fontWeight = FontWeight.Normal,
                text = selectedMovement.description,
                color = WhiteText,
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
                color = WhiteText,
                fontSize = Font20sp,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                fontWeight = FontWeight.Normal,
                text = selectedMovement.amount.toString(),
                color = WhiteText,
                fontSize = Font20sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
