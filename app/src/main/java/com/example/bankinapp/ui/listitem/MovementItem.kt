package com.example.bankinapp.ui.listitem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.bankinapp.R
import com.example.bankinapp.data.db.entities.Movements
import com.example.bankinapp.ui.theme.Dimen10dp
import com.example.bankinapp.ui.theme.Dimen15dp
import com.example.bankinapp.ui.theme.Dimen20dp
import com.example.bankinapp.ui.theme.Dimen3dp
import com.example.bankinapp.ui.theme.Dimen5dp
import com.example.bankinapp.ui.theme.Font12sp
import com.example.bankinapp.ui.theme.Font15sp
import com.example.bankinapp.ui.theme.LightBlue
import com.example.bankinapp.ui.theme.WhiteText


@Composable
fun MovementItemList(movements: Movements, click: () -> Unit) {

    Column(Modifier.padding(start = Dimen10dp, end = Dimen15dp, bottom = Dimen20dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    click.invoke()
                }
        ) {

            Text(
                modifier = Modifier
                    .padding(start = Dimen3dp)
                    .align(Alignment.TopStart),
                text = movements.date,
                color = Color.White,
                fontSize = Font15sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                modifier = Modifier
                    .padding(end = Dimen5dp)
                    .align(Alignment.TopEnd),
                text = stringResource(id = R.string.detail_screen_amount, movements.amount.toString()),
                color = WhiteText,
                fontSize = Font15sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                modifier = Modifier
                    .padding(start = Dimen3dp, top = Dimen15dp)
                    .align(Alignment.CenterStart),
                text = movements.description,
                color = WhiteText,
                fontSize = Font12sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        HorizontalDivider(
            modifier = Modifier
                .padding(Dimen3dp)
                .fillMaxHeight(),
            color = LightBlue
        )
    }
}
