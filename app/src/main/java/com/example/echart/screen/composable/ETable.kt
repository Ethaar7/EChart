package com.example.echart.screen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.echart.screen.home.TableEntry
import com.example.echart.ui.theme.CardColor
import com.example.echart.ui.theme.Typography
import com.example.echart.ui.theme.blue
import com.example.echart.ui.theme.text37
import com.example.echart.ui.theme.text87

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    onTextChange: (String) -> Unit
) {
    BasicTextField(
        value = text,
        onValueChange = { onTextChange(it) },
        Modifier
            .border(1.dp, text37)
            .weight(weight)
            .padding(8.dp)
    )
}


@Composable
fun ETable() {
    val column1Weight = .3f // 30%
    val column2Weight = .3f // 30%
    val column3Weight = .3f // 30%

    val tableData = remember { mutableStateListOf<TableEntry>() }

    Column(
        Modifier
            .wrapContentSize()
            .padding(16.dp)
    ) {
        Row(Modifier.background(CardColor), horizontalArrangement = Arrangement.Center) {
            TableCell(
                text = "Class",
                weight = column1Weight
            ) {}
            TableCell(
                text = "Fraq",
                weight = column2Weight
            ) {}
            TableCell(
                text = "mid",
                weight = column3Weight
            ) {}
        }

        tableData.forEachIndexed { index, entry ->
            Row(Modifier.fillMaxWidth()) {
                TableCell(
                    text = entry.first,
                    weight = column1Weight
                ) {
                    tableData[index] = entry.copy(first = it)
                }
                TableCell(
                    text = entry.second,
                    weight = column2Weight
                ) {
                    tableData[index] = entry.copy(second = it)
                }
                TableCell(
                    text = entry.third,
                    weight = column3Weight
                ) {
                    tableData[index] = entry.copy(third = it)
                }
            }
        }

        Button(
            onClick = {
                tableData.add(TableEntry("", "", ""))
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
fun kkki() {
    ETable()
}