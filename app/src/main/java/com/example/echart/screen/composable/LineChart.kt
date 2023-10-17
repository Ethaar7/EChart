package com.example.echart.screen.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.aay.compose.baseComponents.model.GridOrientation
import com.aay.compose.lineChart.LineChart
import com.aay.compose.lineChart.model.LineParameters
import com.aay.compose.lineChart.model.LineType
import com.example.echart.screen.home.HomeUiState

@Composable
fun LineChartSample(
    state : HomeUiState= HomeUiState()
) {

    val testLineParameters: List<LineParameters> = listOf(
        LineParameters(
            label = "",
            data = state.frequencyList,
            lineColor = Color.Gray,
            lineType = LineType.DEFAULT_LINE,
            lineShadow = true,
        )
    )

    Box(Modifier) {
        LineChart(
            modifier = Modifier.fillMaxSize(),
            linesParameters = testLineParameters,
            isGrid = true,
            gridColor = Color.Blue,
            xAxisData = state.classBoundariesList,
            animateChart = true,
            showGridWithSpacer = true,
            yAxisStyle = TextStyle(
                fontSize = 14.sp,
                color = Color.Gray,
            ),
            xAxisStyle = TextStyle(
                fontSize = 14.sp,
                color = Color.Gray,
                fontWeight = FontWeight.W400
            ),
            yAxisRange = 4,
            oneLineChart = false,
            gridOrientation = GridOrientation.VERTICAL
        )
    }
}

@Preview
@Composable
fun iiii(){
    LineChartSample()
}