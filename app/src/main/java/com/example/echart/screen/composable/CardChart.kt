package com.example.echart.screen.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.echart.R
import com.example.echart.ui.theme.CardColor
import com.example.echart.ui.theme.primary

@Composable
fun CardChart(
    onCardSelected: (Int) -> Unit,
     isBarChartVisible: Boolean = false,
    isLineChartViable: Boolean = false
) {

        AnimatedVisibility(visible = isBarChartVisible ) {
            Box(modifier = Modifier.fillMaxSize().background(primary)) {

            }
        }
        AnimatedVisibility(visible = isLineChartViable ) {
            Box(modifier = Modifier.fillMaxSize().background(Color.Red)) {

            }
        }

        AnimatedVisibility(visible = (!isLineChartViable && !isBarChartVisible) ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .width(160.dp)
                        .height(152.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(CardColor)
                        .clickable(onClick = { onCardSelected(0) }),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.column_chart_ascending),
                        contentDescription = "",
                        modifier = Modifier.size(128.dp),
                        tint = primary
                    )
                }

                Box(
                    modifier = Modifier
                        .width(160.dp)
                        .height(152.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(CardColor)
                        .clickable(onClick = { onCardSelected(1) }),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.area_chart_curved),
                        contentDescription = "",
                        modifier = Modifier.size(128.dp),
                        tint = primary
                    )
                }
            }

        }

    }


