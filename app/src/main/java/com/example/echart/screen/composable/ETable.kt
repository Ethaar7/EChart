package com.example.echart.screen.composable

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.echart.R
import com.example.echart.screen.home.HomeUiState
import com.example.echart.screen.home.TableEntry
import com.example.echart.ui.theme.CardColor
import com.example.echart.ui.theme.Typography
import com.example.echart.ui.theme.blue
import com.example.echart.ui.theme.primary
import com.example.echart.ui.theme.text37
import com.example.echart.ui.theme.text60
import com.example.echart.ui.theme.text87

@Composable
fun RowScope.TableCell(weight: Float, content: @Composable () -> Unit) {
    Box(
            modifier = Modifier
                .border(1.dp, text37)
                .weight(weight)
                .padding(8.dp)
    ) {
        content()
    }
}


@Composable
fun ETable(
    data: HomeUiState = HomeUiState(),
    onAddClicked: () -> Unit,
    onChangeClassLimits: (String, String, String) -> Unit,
    onChangeFrequency: (String, String) -> Unit,
) {
    val column1Weight = .3f // 30%
    val column2Weight = .3f // 30%
    val column3Weight = .3f // 30%

    Column(
            Modifier
                .wrapContentSize()
                .padding(16.dp)
    ) {
        EETable(
                data = data.table,
                headers = data.headers,
                modifier = Modifier,
                key = { it.id }
        ) { entry ->
            TableCell(weight = column1Weight) {
                Row {
                    BasicTextField(
                            value = entry.classLimits.first,
                            onValueChange = {
                                onChangeClassLimits(
                                        it,
                                        entry.classLimits.second,
                                        entry.id
                                )
                            },
                    )
                    BasicTextField(
                            value = entry.classLimits.second,
                            onValueChange = {
                                onChangeClassLimits(
                                        entry.classLimits.first,
                                        it,
                                        entry.id
                                )
                            }
                    )
                }
            }
            TableCell(weight = column2Weight) {
                BasicTextField(
                        value = entry.frequency,
                        onValueChange = { onChangeFrequency(it, entry.id) },
                )
            }
            TableCell(weight = column3Weight) {
                Text(text = entry.midPoint)
            }
        }
        Button(
                onClick = onAddClicked,
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
            onChangeClassLimits = { _, _, _ -> },
            onChangeFrequency = { _, _ -> }
    )
}


data class Header(val text: String, val weight: Float = 1f)

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun <T> ColumnScope.EETable(
    data: List<T>,
    headers: List<Header>,
    modifier: Modifier = Modifier,
    key: ((item: T) -> Any)? = null,
    shape: Shape = RoundedCornerShape(8.dp),
    rowPadding: PaddingValues = PaddingValues(12.dp),
    maxHeight: Dp = 24.dp,
    border: Dp = 1.dp,
    borderColor: Color = text60,
    headerColor: Color = primary,
    rowsColor: Color = CardColor,
    rowContent: @Composable RowScope.(T) -> Unit,
) {
        LazyColumn(
                modifier = modifier
                    .clip(shape = shape)
                    .border(border, borderColor, shape)
        ) {
            stickyHeader { TableHeader(headerColor, rowPadding, maxHeight, headers, border, borderColor) }
            if (data.isNotEmpty()) {
                items(data, key = key) { data ->
                    TableRow(
                            rowsColor,
                            rowPadding,
                            maxHeight,
                            rowContent,
                            data,
                            border,
                            borderColor
                    )
                }
            }
        }
}

@Composable
private fun <T> TableRow(
    rowsColor: Color,
    rowPadding: PaddingValues,
    maxHeight: Dp,
    rowContent: @Composable() (RowScope.(T) -> Unit),
    data: T,
    border: Dp,
    borderColor: Color
) {
    Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(rowsColor)
                .padding(rowPadding)
                .heightIn(max = maxHeight),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        rowContent(data)
    }
    Divider(
            Modifier.fillMaxWidth(),
            thickness = border,
            color = borderColor
    )
}

@Composable
private fun LazyItemScope.TableHeader(
    headerColor: Color,
    rowPadding: PaddingValues,
    maxHeight: Dp,
    headers: List<Header>,
    border: Dp,
    borderColor: Color
) {
    Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(headerColor)
                .padding(rowPadding)
                .heightIn(max = maxHeight),
            verticalAlignment = Alignment.CenterVertically
    ) {
        headers.forEach { header ->
            Text(
                    header.text,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(header.weight)
            )
        }
    }
    Divider(Modifier.fillMaxWidth(), thickness = border, color = borderColor)
}
