package com.example.echart.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.echart.R
import com.example.echart.screen.composable.CardChart
import com.example.echart.screen.composable.ETable
import com.example.echart.screen.composable.TopBar
import com.example.echart.ui.theme.Typography
import com.example.echart.ui.theme.primary
import com.example.echart.ui.theme.text87

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopBar()
        }
    ) { PaddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingValues)
        ) {
            item {
                CardChart()
            }
            item {
                    ETable()
            }
            item {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(primary)
                ) {
                    Text(text = "Run", color = text87, style = Typography.bodyLarge)
                }
            }
        }
    }
}



@Preview
@Composable
fun iju(){
    HomeScreen()
}