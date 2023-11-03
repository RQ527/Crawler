package com.wssg.crawler.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wssg.crawler.activity.RouteConfig

/**
 * ...
 * @author RQ527 (Ran Sixiang)
 * @email 1799796122@qq.com
 * @date 2023/10/30
 * @Description:
 */
@Composable
fun HomePage(modifier: Modifier = Modifier, onClicked: ((route: String) -> Unit)) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp)
                    .clickable {
                        onClicked("${RouteConfig.EDIT_PAGE}/false")
                    }
            ) {
                Text(
                    text = "查课表", modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight(), textAlign = TextAlign.Center
                )
            }
        }
        item {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp)
                    .clickable {
                        onClicked("${RouteConfig.EDIT_PAGE}/true")
                    }
            ) {
                Text(
                    text = "运行自定义py脚本", modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight(), textAlign = TextAlign.Center
                )
            }
        }
    }
}