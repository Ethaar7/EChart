package com.example.echart.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.echart.R
import com.example.echart.screen.composable.CardChart
import com.example.echart.screen.composable.ETable
import com.example.echart.screen.composable.TopBar
import com.example.echart.screen.main.LocalNavigationProvider
import com.example.echart.ui.theme.Typography
import com.example.echart.ui.theme.blue
import com.example.echart.ui.theme.primary
import com.example.echart.ui.theme.text87

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val navController = LocalNavigationProvider.current

    HomeContent(
        state = state,
        onClickAdd = viewModel::addRow,
        onChangeClassLimits = viewModel::onChangeClassLimits,
        onChangeFrequency = viewModel::onChangeFrequency,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    state: HomeUiState,
    onClickAdd: () -> Unit = {},
    onChangeClassLimits: (String, String, Int) -> Unit,
    onChangeFrequency: (String, Int) -> Unit,
) {
    Scaffold(topBar = { TopBar() }, bottomBar = {
        Column() {
            Button(
                onClick = { onClickAdd() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(blue)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_square),
                        contentDescription = "",
                        tint = text87
                    )
                    Text(text = "Add label", color = text87, style = Typography.bodySmall)
                }
            }
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(primary)
            ) {
                Text(text = "Run", color = text87, style = Typography.bodyLarge)
            }
        }
    }) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
        ) {
            CardChart()
            ETable(
                data = state,
                onChangeClassLimits = onChangeClassLimits,
                onChangeFrequency = onChangeFrequency,
            )

        }
    }
}


@Preview
@Composable
fun iju() {
    HomeScreen()
}