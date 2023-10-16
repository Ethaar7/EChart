package com.example.echart.screen.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.echart.R
import com.example.echart.screen.home.HomeUiState
import com.example.echart.screen.home.TableEntry
import com.example.echart.ui.theme.CardColor
import com.example.echart.ui.theme.Typography
import com.example.echart.ui.theme.blue
import com.example.echart.ui.theme.text37
import com.example.echart.ui.theme.text87

@Composable
fun RowScope.TableCell(weight: Float,content: @Composable () -> Unit) {
    Box(  modifier = Modifier
        .border(1.dp, text37)
        .weight(weight)
        .padding(8.dp)
    ){
        content()
    }
}


@Composable
fun ETable(
    data:HomeUiState = HomeUiState(),
    onAddClicked: () -> Unit,
    onChangeClassLimits: (String, String) -> Unit,
    onChangeFrequency: (String) -> Unit ,
) {
    val column1Weight = .3f // 30%
    val column2Weight = .3f // 30%
    val column3Weight = .3f // 30%

    Column(
            Modifier
                .wrapContentSize()
                .padding(16.dp)
    ) {
        Row(Modifier.background(CardColor), horizontalArrangement = Arrangement.Center) {
            TableCell(weight = column1Weight, content = { Text(text = "Class") })
            TableCell(weight = column2Weight, content = { Text(text = "Fraq") })
            TableCell(weight = column3Weight, content = { Text(text = "Mid") })
        }

        data.table.forEachIndexed { index, entry ->
            Row(Modifier.fillMaxWidth()) {
                TableCell(weight = column1Weight){
                    Row {
                        BasicTextField(
                                value = data.classLimits.first,
                                onValueChange = { onChangeClassLimits(it, data.classLimits.second) },
                        )
                        BasicTextField(
                                value = data.classLimits.second,
                                onValueChange = { onChangeClassLimits(data.classLimits.second, it) },
                        )
                    }
                }
                TableCell(weight = column2Weight){
                    BasicTextField(
                            value = data.frequency,
                            onValueChange = onChangeFrequency,
                    )
                }
                TableCell(weight = column3Weight){
                    Text(text = data.midPoint)
                }
            }
        }

        Button(
                onClick = {
                    data.table.add(TableEntry("" ,"", ""))
                          Log.d("ETable", "ETable: ${data.table}")
                          },
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
    }
}


@Preview(showSystemUi = true)
@Composable
fun TablePreview() {
    ETable(
            data = HomeUiState(),
            onAddClicked = {},
            onChangeClassLimits = { _, _ -> },
            onChangeFrequency = {},
    )
}