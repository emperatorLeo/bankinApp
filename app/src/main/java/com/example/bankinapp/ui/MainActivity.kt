package com.example.bankinapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.bankinapp.ui.navigation.AppNavigation
import com.example.bankinapp.ui.theme.BankinAppTheme
import com.example.bankinapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankinAppTheme {
                AppNavigation(viewModel = viewModel)
            }
        }
    }
}
