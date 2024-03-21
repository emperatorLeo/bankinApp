package com.example.bankinapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bankinapp.R
import com.example.bankinapp.model.UserInformation
import com.example.bankinapp.ui.components.CustomInputField
import com.example.bankinapp.ui.components.TextFieldType.EMAIL
import com.example.bankinapp.ui.components.TextFieldType.PASSWORD
import com.example.bankinapp.ui.navigation.Screen
import com.example.bankinapp.ui.states.InputTextState
import com.example.bankinapp.ui.theme.BrightPurple
import com.example.bankinapp.ui.theme.Dimen10dp
import com.example.bankinapp.ui.theme.Dimen20dp
import com.example.bankinapp.ui.theme.Dimen40dp
import com.example.bankinapp.ui.theme.Dimen50dp
import com.example.bankinapp.ui.theme.Dimen5dp
import com.example.bankinapp.ui.theme.Font15sp
import com.example.bankinapp.ui.theme.Font20sp
import com.example.bankinapp.ui.theme.Font30sp
import com.example.bankinapp.ui.theme.GrayDisableText
import com.example.bankinapp.ui.theme.Pink40
import com.example.bankinapp.ui.theme.Purple40
import com.example.bankinapp.ui.theme.Purple80
import com.example.bankinapp.ui.theme.PurpleGrey80
import com.example.bankinapp.ui.theme.White
import com.example.bankinapp.ui.viewmodel.MainViewModel
import com.example.bankinapp.util.TestTags.COMPLETE_SIGN_UP_BUTTON

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    viewModel: MainViewModel,
    navController: NavController
) {

    val userInformation = remember {
        mutableStateOf(UserInformation.userEmpty)
    }

    val inputTextState = remember {
        mutableStateOf(InputTextState.idle)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)

    ) {
        TopAppBar(title = { }, navigationIcon = {
            Icon(
                modifier = Modifier
                    .padding(start = Dimen5dp)
                    .clickable {
                        navController.popBackStack()
                    },
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "",
                tint = Color.White
            )
        }, colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Purple80))

        Text(
            modifier = Modifier
                .padding(top = Dimen20dp)
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            text = stringResource(id = R.string.sign_up_screen_title),
            color = Purple40,
            fontSize = Font30sp,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .padding(top = Dimen10dp, bottom = 20.dp, start = Dimen10dp, end = Dimen10dp)
                .align(Alignment.Start),
            fontWeight = FontWeight.SemiBold,
            text = stringResource(id = R.string.sign_up_screen_disclaimer),
            color = Pink40,
            fontSize = Font15sp,
            textAlign = TextAlign.Center
        )

        CustomInputField(
            label = stringResource(id = R.string.sign_up_screen_email_label),
            modifier = Modifier.padding(top = Dimen20dp, bottom = Dimen10dp),
            textFieldType = EMAIL,
            backgroundColor = PurpleGrey80,
            imageResource = R.drawable.ic_email,
            isValidEmail = {
                inputTextState.value = inputTextState.value.copy(isEmailOk = it)
            }
        ) { userInformation.value = userInformation.value.copy(email = it) }

        CustomInputField(
            label = stringResource(id = R.string.sign_up_screen_password_label),
            textFieldType = PASSWORD,
            modifier = Modifier.padding(bottom = Dimen10dp),
            PurpleGrey80,
            imageResource = R.drawable.ic_password,
        ) {
            inputTextState.value = inputTextState.value.copy(isPasswordOk = it.isNotEmpty())
            userInformation.value = userInformation.value.copy(password = it)
        }

        CustomInputField(
            label = stringResource(id = R.string.sign_up_screen_name_label),
            modifier = Modifier.padding(bottom = Dimen10dp),
            backgroundColor = PurpleGrey80,
            imageResource = R.drawable.ic_user,
            minLengthAllowed = 3,
            reachMinAllowed = {
                inputTextState.value = inputTextState.value.copy(isNameOk = it)
            }
        ) { userInformation.value = userInformation.value.copy(name = it) }

        CustomInputField(
            label = stringResource(id = R.string.sign_up_screen_surname_label),
            modifier = Modifier.padding(bottom = Dimen10dp),
            backgroundColor = PurpleGrey80,
            imageResource = R.drawable.ic_user,
            minLengthAllowed = 2,
            reachMinAllowed = {
                inputTextState.value = inputTextState.value.copy(isSurnameOk = it)
            }
        ) { userInformation.value = userInformation.value.copy(surname = it) }

        Button(
            modifier = Modifier
                .padding(top = Dimen10dp, start = Dimen40dp, end = Dimen40dp)
                .fillMaxWidth()
                .height(Dimen50dp)
                .testTag(COMPLETE_SIGN_UP_BUTTON),
            onClick = {
                viewModel.setUserInformation(userInformation.value)
                navController.navigate(Screen.TakePhoto.route)
            },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = BrightPurple,
                contentColor = Color.White,
                disabledContainerColor = Color.LightGray,
                disabledContentColor = GrayDisableText
            ),
            enabled = inputTextState.value.areAllOk()
        ) {
            Text(
                text = stringResource(id = R.string.sign_up_screen_next_label),
                fontSize = Font20sp
            )
        }
    }
}

