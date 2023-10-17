package com.example.echart.screen.composable

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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.echart.screen.home.HomeUiState
import com.example.echart.ui.theme.CardColor
import com.example.echart.ui.theme.primary
import com.example.echart.ui.theme.text37
import com.example.echart.ui.theme.text60

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
    onChangeClassLimits: (String, String, Int) -> Unit,
    onChangeFrequency: (String, Int) -> Unit,
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
        ) { entry,index ->
            TableCell(weight = column1Weight) {
                BasicTextField(
                    value = "${entry.classLimits.first} - ${entry.classLimits.second}",
                    onValueChange = {
                        val values = it.split("-")
                        if (values.size == 2) {
                            onChangeClassLimits(
                                values[0].trim(),
                                values[1].trim(),
                                index
                            )
                        }
                    }
                )
            }
            TableCell(weight = column2Weight) {
                BasicTextField(
                        value = entry.frequency,
                        onValueChange = { onChangeFrequency(it, index) },
                )
            }
            TableCell(weight = column3Weight) {
                Text(text = entry.midPoint)
            }
        }

    }
}


@Preview(showSystemUi = true)
@Composable
fun TablePreview() {
    ETable(
            data = HomeUiState(),
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
    maxHeight: Dp = 48.dp,
    border: Dp = 1.dp,
    borderColor: Color = text60,
    headerColor: Color = primary,
    rowsColor: Color = CardColor,
    rowContent: @Composable RowScope.(T,Int) -> Unit,
) {
        LazyColumn(
                modifier = modifier
                    .clip(shape = shape)
                    .border(border, borderColor, shape)
        ) {
            stickyHeader { TableHeader(headerColor, rowPadding, maxHeight, headers, border, borderColor) }
            if (data.isNotEmpty()) {
                itemsIndexed(data) { index,data ->
                    TableRow(
                            rowsColor,
                            rowPadding,
                            maxHeight,
                            rowContent,
                            data,
                            index,
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
    rowContent: @Composable() (RowScope.(T,Int) -> Unit),
    data: T,
    index:Int,
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
        rowContent(data,index)
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
