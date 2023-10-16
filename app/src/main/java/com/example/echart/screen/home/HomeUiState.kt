package com.example.echart.screen.home

data class HomeUiState(
    val table: MutableList<TableEntry> = mutableListOf(),
    val classLimits: Pair<String, String> = Pair("", ""),
    val frequency: String = "",
    val midPoint: String = "",
    val classBoundaries: String = "",
)

data class TableEntry(
    val first: String = "",
    val second: String = "",
    val third: String = "",
)