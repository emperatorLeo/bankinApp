package com.example.bankinapp.ui.listitem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankinapp.data.db.entities.Movements
import com.example.bankinapp.ui.theme.BrightPurple
import com.example.bankinapp.ui.theme.Purple80

@Composable
fun MovementItemList(movements: Movements, click: () -> Unit) {
    val color = if (movements.amount < 0) Red else Green
    Column(Modifier.padding(start = 10.dp, end = 15.dp,bottom = 20.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(
                Purple80
            )
            .clickable {
                click.invoke()
            }) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = movements.description,
                color = BrightPurple,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .align(Alignment.CenterVertically),
                text = "$ ${movements.amount}",
                color = color,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Divider(modifier = Modifier.height(1.dp).fillMaxHeight().background(Black))
    }
}