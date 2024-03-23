package com.example.bankinapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionResult
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.bankinapp.R
import com.example.bankinapp.ui.navigation.Screen
import com.example.bankinapp.ui.theme.Dimen100dp
import com.example.bankinapp.ui.theme.Dimen200dp
import com.example.bankinapp.ui.theme.Dimen20dp
import com.example.bankinapp.ui.theme.Dimen40dp
import com.example.bankinapp.ui.theme.Dimen50dp
import com.example.bankinapp.ui.theme.LightBlue
import com.example.bankinapp.ui.theme.WhiteText
import com.example.bankinapp.util.Const.ANIMATION_URL
import kotlinx.coroutines.delay

@Composable
fun SuccessScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlue)
    ) {

        val lottieComposition by rememberLottieComposition(spec = LottieCompositionSpec.Url(ANIMATION_URL))
        val compositionResult: LottieCompositionResult =
            rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.success_animation))
        val progress by animateLottieCompositionAsState(composition = lottieComposition)

        LottieAnimation(
            modifier = Modifier
                .padding(top = Dimen100dp)
                .size(Dimen200dp)
                .align(Alignment.CenterHorizontally),
            composition = lottieComposition,
            progress = { progress },
        )

        Text(
            modifier = Modifier.padding(start = Dimen40dp, end = Dimen40dp, top = Dimen50dp),
            text = stringResource(id = R.string.sign_up_screen_success_message_spa),
            color = WhiteText,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier.padding(start = Dimen40dp, end = Dimen40dp, top = Dimen20dp),
            text = stringResource(id = R.string.sign_up_screen_success_message),
            color = WhiteText,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        LaunchedEffect(compositionResult.isComplete) {
            delay(4500)
            navController.popBackStack(Screen.Login.route,false)
        }
    }
}