package com.example.echart.screen.home

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

    fun onChangeClassLimits(maxNum: String, minNum: String,id:String) {
        _state.update { it.copy(table = _state.value.table.map { row ->
                if (row.id == id) {
                    row.copy(classLimits = Pair(maxNum, minNum))
                } else {
                    row
                }
            }.toMutableList()
        ) }
        onChangeMidPoint(maxNum,minNum)
    }

    fun onChangeFrequency(frequency: String,id:String) {
        _state.update { it.copy(table = _state.value.table.map { row ->
            if (row.id == id) {
                row.copy(frequency = frequency)
            } else {
                row
            }
        }.toMutableList()
        ) }
    }
    private fun onChangeMidPoint(max: String, min: String) {
        _state.update { it.copy(table = it.table.map { row ->
            if (max == row.classLimits.first && min == row.classLimits.second) {
                row.copy(midPoint = dataSource.calculateMidPoint(
                    row.classLimits.first.toInt(),
                    row.classLimits.second.toInt()
                ).toString())
            } else {
                row
            }
        }.toMutableList()
        )}
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

}