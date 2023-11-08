package com.example.echart.screen.home

import com.example.echart.screen.composable.Header

data class HomeUiState(
    val table: MutableList<TableEntry> = mutableListOf(),
    val classBoundariesList: List<String> = listOf(),
    val frequencyList:List<Double> = listOf(),
    val isBarChartVisible: Boolean = false,
    val isLineChartViable: Boolean = false,
    val isShowBake : Boolean = false ,
    ) {
    val headers: List<Header> = listOf(
        Header("Class", 3f),
        Header("Freq", 3f),
        Header("Mid", 3f),
    )
}

data class TableEntry(
    val id: String = "",
    val classLimits: Pair<String, String> = Pair("", ""),
    val frequency: String = "",
    val midPoint: String = "",
    val classBoundaries: String = "",
    )