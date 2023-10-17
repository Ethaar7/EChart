package com.example.echart.screen.home

import com.example.echart.screen.composable.Header

data class HomeUiState(
    val table: MutableList<TableEntry> = mutableListOf(),
    var isBarChartVisible: Boolean = false,
    val isLineChartViable: Boolean = false,
    val isShow : Boolean = false ,
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