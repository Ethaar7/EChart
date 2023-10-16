package com.example.echart.screen.home

import androidx.lifecycle.ViewModel
import com.example.echart.data.IChartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataSource: IChartRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        _state.update { it.copy(table = dataSource.getTable()) }
      //  addRow()
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
        onChangeMidPoint(id)
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

    private fun onChangeMidPoint(id:String) {
        _state.update { it.copy(table = it.table.map { row->
                if (row.id == id) {
                    row.copy(midPoint = dataSource.calculateMidPoint(
                            row.classLimits.first.toInt(),
                            row.classLimits.second.toInt()
                    ).toString()
                    )
                } else {
                    row
                }
            }.toMutableList()
        )
        }
    }
    fun addRow() {
//        if (!(state.value.classLimits.first.isBlank() &&
//            state.value.classLimits.second.isBlank() &&
//            state.value.frequency.isBlank() &&
//            state.value.classBoundaries.isBlank())
//            ){
//            dataSource.addTableEntry(
//                    TableEntry(
//                            state.value.classLimits.toString(),
//                            state.value.frequency,
//                            state.value.classBoundaries
//                    )
//            )
//        }
    }

}