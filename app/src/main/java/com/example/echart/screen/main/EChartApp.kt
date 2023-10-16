package com.example.echart.screen.main

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.echart.screen.home.HomeScreen
import com.example.echart.ui.theme.EChartTheme


val LocalNavigationProvider = staticCompositionLocalOf<NavHostController> {
    error("No navigation host controller provided.")
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EChartApp() {
    CompositionLocalProvider(LocalNavigationProvider provides rememberNavController()) {
        EChartTheme {
            HomeScreen()
        }
    }
}