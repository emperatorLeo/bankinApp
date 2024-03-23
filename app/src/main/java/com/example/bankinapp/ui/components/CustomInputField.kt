package com.example.bankinapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.bankinapp.R
import com.example.bankinapp.ui.components.TextFieldType.EMAIL
import com.example.bankinapp.ui.components.TextFieldType.NAME
import com.example.bankinapp.ui.components.TextFieldType.PASSWORD
import com.example.bankinapp.ui.theme.Dimen10dp
import com.example.bankinapp.ui.theme.Dimen20dp
import com.example.bankinapp.ui.theme.Dimen40dp
import com.example.bankinapp.ui.theme.Dimen5dp
import com.example.bankinapp.ui.theme.Dimen8dp
import com.example.bankinapp.ui.theme.WhiteText
import com.example.bankinapp.util.EmailHelper

@Composable
fun CustomInputField(
    label: String,
    textFieldType: TextFieldType = NAME,
    modifier: Modifier,
    backgroundColor: Color,
    imageResource: Int,
    minLengthAllowed: Int = 0,
    isValidEmail: (Boolean) -> Unit = {},
    reachMinAllowed: (Boolean) -> Unit = {},
    onValueChange: (String) -> Unit
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    var visibility by rememberSaveable {
        mutableStateOf(textFieldType == NAME || textFieldType == EMAIL)
    }

    var isEmailValid by rememberSaveable {
        mutableStateOf(true)
    }

    var reachMinCharAllowed by rememberSaveable {
        mutableStateOf(true)
    }

    val icon = if (visibility) {
        painterResource(id = R.drawable.ic_visibility)
    } else {
        painterResource(id = R.drawable.ic_disable_visibility)
    }

    val color = if (isEmailValid) WhiteText else Color.Red

    Column(
        modifier
            .padding(top = Dimen5dp, start = Dimen20dp)
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Row {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = "edittext icon",
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            TextField(
                modifier = Modifier
                    .padding(start = Dimen10dp)
                    .align(Alignment.CenterVertically),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = color,
                    focusedContainerColor = backgroundColor,
                    unfocusedTextColor = color,
                    unfocusedContainerColor = backgroundColor,
                    cursorColor = color,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                singleLine = true,
                shape = RoundedCornerShape(Dimen8dp),
                value = text,
                label = { Text(text = label, color = color) },
                onValueChange = {
                    val currentText = it.trim()
                    text = if (textFieldType == NAME) {
                        val filteredText = currentText.filter { char ->
                            !char.isDigit()
                        }
                        onValueChange(filteredText)
                        filteredText

                    } else {
                        onValueChange(currentText)
                        currentText
                    }

                    if (currentText.isNotEmpty()) {
                        if (textFieldType == EMAIL) {
                            isEmailValid = EmailHelper.isEmailValid(currentText)
                            isValidEmail(isEmailValid)
                        } else {
                            isEmailValid = true
                        }
                        reachMinCharAllowed =
                            if (textFieldType == NAME && minLengthAllowed != 0 && currentText.length < minLengthAllowed) {
                                reachMinAllowed(false)
                                false
                            } else {
                                reachMinAllowed(true)
                                true
                            }
                    } else {
                        isEmailValid = true
                        reachMinCharAllowed = true
                    }
                },
                trailingIcon = {
                    if (textFieldType == PASSWORD) {
                        IconButton(onClick = {
                            visibility = !visibility
                        }) {
                            Icon(
                                painter = icon,
                                contentDescription = "password visibility icon",
                                tint = WhiteText
                            )
                        }
                    }
                },
                visualTransformation = if (!visibility) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                }
            )
        }
        if (textFieldType == EMAIL && !isEmailValid)
            Text(
                modifier = Modifier.padding(start = Dimen40dp),
                text = stringResource(id = R.string.component_email_format_error),
                color = Color.Red
            )
        if (textFieldType == NAME && !reachMinCharAllowed)
            Text(
                modifier = Modifier.padding(start = Dimen40dp),
                text = stringResource(id = R.string.component_min_limit_error, minLengthAllowed),
                color = Color.Red
            )
    }
}

enum class TextFieldType {
    NAME, PASSWORD, EMAIL
}


