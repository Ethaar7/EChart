package com.example.echart.data

import com.example.echart.screen.home.TableEntry
import javax.inject.Inject

class ChartRepository @Inject constructor() : IChartRepository {

    private val table: MutableList<TableEntry> = mutableListOf()


    override fun getTable(): MutableList<TableEntry> {
        return table
    }

    override fun addTableEntry(entry: TableEntry) {
        table.add(entry)
    }

    override fun calculateMidPoint(maxNumber: Int, minNumber: Int): Int {
        return (maxNumber + minNumber) / 2
    }


    override fun calculateClassBoundaries(maxNumber: Int, minNumber: Int): String {
        val firstValue = maxNumber - 0.5
        val secondValue = minNumber + 0.5
        return "$firstValue-$secondValue"
    }
}

interface IChartRepository {
    fun getTable(): MutableList<TableEntry>
    fun addTableEntry(entry: TableEntry)
    fun calculateMidPoint(maxNumber: Int, minNumber: Int): Int
    fun calculateClassBoundaries(maxNumber: Int, minNumber: Int): String
}
