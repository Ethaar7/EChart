package com.example.echart.screen.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aay.compose.barChart.BarChart
import com.aay.compose.barChart.model.BarParameters
import com.example.echart.ui.theme.primary

@Composable
fun BarChartSample(
    classBoundaries: List<String>,
    frequency: List<Double>,
 ) {

    val testBarParameters: List<BarParameters> = listOf(
        BarParameters(
            dataName = "",
            data = frequency,
            barColor = primary
        )
    )

    Box(Modifier.fillMaxSize()) {
        BarChart(
            chartParameters = testBarParameters,
            gridColor = Color.DarkGray,
            xAxisData = classBoundaries,
            showGridWithSpacer =false,
            isShowGrid = true,
            animateChart = true,
            spaceBetweenBars = 2.dp,
            yAxisStyle = TextStyle(
                fontSize = 12.sp,
                color = Color.Black,
                fontWeight = FontWeight.W400
            ),
            xAxisStyle = TextStyle(
                fontSize = 12.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.W400
            ),
            yAxisRange = 4,
            barWidth = 24.dp
        )
    }
}

