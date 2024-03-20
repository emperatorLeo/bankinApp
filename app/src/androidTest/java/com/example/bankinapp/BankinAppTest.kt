package com.example.bankinapp

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.bankinapp.ui.MainActivity
import com.example.bankinapp.ui.navigation.Screen
import com.example.bankinapp.ui.theme.BankinAppTheme
import com.example.bankinapp.util.TestTags.COMPLETE_SIGN_UP_BUTTON
import com.example.bankinapp.util.TestTags.SIGN_UP_BUTTON
import org.junit.Rule
import org.junit.Test

class BankinAppTest {

    @get:Rule(order = 0)
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    @Test
    fun app_shows_login_screen() {
        composeTestRule.activity.setContent {
            BankinAppTheme {}

            composeTestRule.onNodeWithText(Screen.Login.route).assertIsDisplayed()
        }
    }

    @Test
    fun when_sign_up_button_is_clicked_sign_up_screen_should_be_shown() {
        composeTestRule.activity.setContent {
            BankinAppTheme {}

            composeTestRule.onNodeWithTag(SIGN_UP_BUTTON).performClick()
            composeTestRule.onNodeWithText(Screen.SignUp.route).assertIsDisplayed()
        }
    }

    @Test
    fun when_signUpButton_is_clicked_on_signUpScreen_homeScreen_should_be_shown() {
        composeTestRule.activity.setContent {
            BankinAppTheme {}

            composeTestRule.onNodeWithTag(SIGN_UP_BUTTON).performClick()
            composeTestRule.onNodeWithTag(COMPLETE_SIGN_UP_BUTTON).performClick()
            composeTestRule.onNodeWithText(Screen.Home.route).assertIsDisplayed()
        }
    }

}