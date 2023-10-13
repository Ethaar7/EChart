package com.example.echart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.echart.screen.home.HomeScreen
import com.example.echart.ui.theme.EChartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EChartTheme {
                HomeScreen()
            }
        }
    }
}
