package com.example.bankinapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.bankinapp.R
import com.example.bankinapp.ui.listitem.MovementItemList
import com.example.bankinapp.ui.navigation.Screen
import com.example.bankinapp.ui.theme.BrightPurple
import com.example.bankinapp.ui.theme.Dimen100dp
import com.example.bankinapp.ui.theme.Dimen10dp
import com.example.bankinapp.ui.theme.Dimen15dp
import com.example.bankinapp.ui.theme.Dimen1dp
import com.example.bankinapp.ui.theme.Dimen20dp
import com.example.bankinapp.ui.theme.Dimen30dp
import com.example.bankinapp.ui.theme.Dimen50dp
import com.example.bankinapp.ui.theme.Dimen5dp
import com.example.bankinapp.ui.theme.Font20sp
import com.example.bankinapp.ui.theme.Font30sp
import com.example.bankinapp.ui.theme.Purple40
import com.example.bankinapp.ui.theme.Purple80
import com.example.bankinapp.ui.theme.PurpleGrey40
import com.example.bankinapp.ui.theme.PurpleGrey80
import com.example.bankinapp.ui.theme.White
import com.example.bankinapp.ui.viewmodel.MainViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel, navController: NavController) {
    val email = viewModel.getUserData().first
    val userData = viewModel.getUserData().second
    val movements = viewModel.getMovementDetail()

    Column(
        Modifier
            .fillMaxSize()
            .background(White)

    ) {

        Button(
            modifier = Modifier
                .padding(end = Dimen10dp, top = Dimen10dp)
                .align(Alignment.End),
            onClick = {
                navController.popBackStack()
            },
            colors = ButtonColors(
                containerColor = Purple80,
                contentColor = Purple40,
                disabledContentColor = PurpleGrey40,
                disabledContainerColor = PurpleGrey80
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Logout,
                contentDescription = "",
                tint = Purple40
            )
        }

        Text(
            modifier = Modifier
                .padding(top = Dimen50dp, bottom = Dimen5dp)
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            text = stringResource(
                id = R.string.home_screen_greeting,
                userData.name,
                userData.lastName
            ),
            color = Purple40,
            fontSize = Font30sp,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .padding(top = Dimen5dp, bottom = Dimen30dp, start = Dimen15dp)
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            text = email,
            color = Purple40,
            fontSize = Font20sp,
            textAlign = TextAlign.Center
        )

        AsyncImage(
            modifier = Modifier
                .padding(bottom = Dimen30dp)
                .size(Dimen100dp)
                .align(Alignment.CenterHorizontally),
            model = userData.imageUrl,
            contentDescription = "Id image"
        )


        Text(
            modifier = Modifier
                .padding(top = Dimen10dp, bottom = Dimen10dp)
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Normal,
            text = stringResource(id = R.string.home_screen_movements),
            color = Purple40,
            fontSize = Font20sp,
            textAlign = TextAlign.Center
        )

        HorizontalDivider(
            Modifier
                .padding(horizontal = Dimen1dp)
                .fillMaxWidth(),
            color = Purple40
        )

        if (movements!!.isEmpty()) {
            Text(
                text = stringResource(id = R.string.home_screen_empty_movements_label),
                modifier = Modifier
                    .padding(top = Dimen30dp, start = Dimen20dp, end = Dimen10dp)
                    .align(Alignment.CenterHorizontally),
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
