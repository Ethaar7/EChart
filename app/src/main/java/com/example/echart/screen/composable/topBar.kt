package com.example.echart.screen.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.echart.R
import com.example.echart.ui.theme.Typography
import com.example.echart.ui.theme.primary

@Composable
fun TopBar(
    isShowBake: Boolean,
    onClickBake: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (isShowBake) {
            IconButton(onClick = onClickBake) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back_ios),
                    contentDescription = ""
                )
            }
        } else {
            Row() {
                Text(
                    text = "E",
                    style = Typography.titleLarge,
                    color = primary
                )
                Text(
                    text = "Chart",
                    style = Typography.titleLarge
                )
            }
        }
        IconButton(onClick = {}) {
            Icon(painter = painterResource(id = R.drawable.menu_dots), contentDescription = "")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun jj() {
    TopBar(isShowBake = true)
}