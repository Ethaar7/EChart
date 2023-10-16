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
        addRow()
    }

    fun onChangeClassLimits(first: String, second: String) {
        _state.update { it.copy(classLimits = Pair(first, second)) }
        onChangeMidPoint()
    }

    fun onChangeFrequency(frequency: String) {
        _state.update { it.copy(frequency = frequency) }
        onChangeMidPoint()
    }

    private fun onChangeMidPoint() {
     with(state.value){
              if (classLimits.first.isNotBlank() && classLimits.second.isNotBlank() && frequency.isNotBlank()) {
                val midPoint = dataSource.calculateMidPoint(classLimits.first.toInt(), classLimits.second.toInt())
                _state.update { it.copy(midPoint = midPoint.toString()) }
              }
       }
    }
    fun addRow() {
        if (!(state.value.classLimits.first.isBlank() &&
            state.value.classLimits.second.isBlank() &&
            state.value.frequency.isBlank() &&
            state.value.classBoundaries.isBlank())
            ){
            dataSource.addTableEntry(
                    TableEntry(
                            state.value.classLimits.toString(),
                            state.value.frequency,
                            state.value.classBoundaries
                    )
            )
        }
    }

}