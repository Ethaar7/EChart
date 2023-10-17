package com.example.echart.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.echart.data.IChartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataSource: IChartRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        _state.update { it.copy(table = dataSource.getTable()) }
        addRow()
    }

    fun onChangeClassLimits(maxNum: String, minNum: String, id: Int) {
        val updatedTable = state.value.table.mapIndexed { index, row ->
            if (index == id) {
                row.copy(classLimits = Pair(maxNum, minNum))
            } else {
                row
            }
        }
        Log.e("table", "updatedTable = $updatedTable")
        _state.value = _state.value.copy(table = updatedTable.toMutableList())
        if (state.value.table[id].classLimits.first.isNotEmpty()
            && state.value.table[id].classLimits.second.isNotEmpty()
        ) {
            onChangeMidPoint(id)
        }
    }


    fun onChangeFrequency(frequency: String, id: Int) {
        val updatedTable = state.value.table.mapIndexed { index, row ->
            if (index == id) {
                row.copy(frequency = frequency)
            } else {
                row
            }
        }
        _state.value = _state.value.copy(table = updatedTable.toMutableList())
        Log.e("table", "updatedTable = $updatedTable")
    }

    private fun onChangeMidPoint(id: Int) {
        Log.e("table", "id = $id")
        val updatedTable = state.value.table.mapIndexed { index, row ->
            if (index == id) {
                row.copy(
                    midPoint = dataSource.calculateMidPoint(
                        row.classLimits.first.toInt(),
                        row.classLimits.second.toInt()
                    ).toString()
                )
            } else {
                row
            }
        }
        _state.value = _state.value.copy(table = updatedTable.toMutableList())
        Log.e("table", "updatedTable = $updatedTable")
    }

    private fun onChangeClassBoundaries(id: Int) {
        Log.e("table", "id = $id")
        val updatedTable = state.value.table.mapIndexed { index, row ->
            if (index == id) {
                row.copy(
                    midPoint = dataSource.calculateClassBoundaries(
                        row.classLimits.first.toInt(),
                        row.classLimits.second.toInt()
                    )
                )
            } else {
                row
            }
        }
        _state.value = _state.value.copy(table = updatedTable.toMutableList())
        Log.e("ClassBoundaries", "ClassBoundaries = $updatedTable")
    }


    fun addRow() {
        val newId = UUID.randomUUID().toString()
        val newTableEntry = TableEntry(
            id = newId,
            classLimits = Pair("", ""),
            frequency = "",
            midPoint = ""
        )
        dataSource.addTableEntry(newTableEntry)

        _state.update { it.copy(table = (_state.value.table + newTableEntry) as MutableList<TableEntry>) }
    }

    fun onCardSelected(index: Int) {
        if (index == 0) {
            _state.update {
                it.copy(
                    isBarChartVisible = true,
                    isLineChartViable = false
                )
            }
        } else {
            _state.update {
                it.copy(
                    isBarChartVisible = false,
                    isLineChartViable = true
                )
            }
        }
        Log.e("chart", "updatedTable = ${index}")
        Log.e("chart", "updatedTable = ${state.value}")

    }

}