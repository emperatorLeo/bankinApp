package com.example.bankinapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.bankinapp.ui.theme.Dimen10dp
import com.example.bankinapp.ui.theme.Dimen40dp
import com.example.bankinapp.ui.theme.Dimen50dp
import com.example.bankinapp.ui.theme.Font20sp
import com.example.bankinapp.ui.theme.White

@Composable
fun ActionButton(modifier: Modifier = Modifier, text: String, color: Color, action: () -> Unit) {
    Box(
        modifier = modifier
            .padding(top = Dimen10dp, start = Dimen40dp, end = Dimen40dp)
            .background(
                color = color,
                shape = CircleShape
            )
            .fillMaxWidth()
            .height(Dimen50dp)
            .clickable { action.invoke() }
    ) {
        Text(
            text = text,
            modifier
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            fontSize = Font20sp,
            color = White
        )
    }
}