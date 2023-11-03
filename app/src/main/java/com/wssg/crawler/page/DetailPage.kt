package com.wssg.crawler.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * ...
 * @author RQ527 (Ran Sixiang)
 * @email 1799796122@qq.com
 * @date 2023/10/30
 * @Description:
 */
@Composable
fun SearchCourse(
    modifier: Modifier = Modifier,
    isPyPage: Boolean = false,
    onClick: (isPyPage:Boolean,content:String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        EditText(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.CenterHorizontally),
            imageVector = if (isPyPage) Icons.Filled.PlayArrow else Icons.Filled.Search,
            hint = if (isPyPage) "输入你的python代码" else "输入你的学号",
            onClick = {
                onClick(isPyPage,it)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditText(
    modifier: Modifier,
    imageVector: ImageVector,
    hint: String,
    onClick: ((content: String) -> Unit)
) {
    // 定义一个可观测的text，用来在TextField中展示
    var text by remember {
        mutableStateOf("")
    }
    TextField(
        modifier = modifier,
        value = text, // 显示文本
        onValueChange = { text = it }, // 文字改变时，就赋值给text
        label = { Text(text = hint) }, // label是Input
        // 头部图标，设置为搜索
        leadingIcon = @Composable {
            Image(
                imageVector = imageVector, // 搜索图标
                contentDescription = null,
                modifier = Modifier.clickable {
                    onClick(text)
                }) // 给图标添加点击事件，点击就吐司提示内容
        },
        // 尾部图标，设置为清除
        trailingIcon = @Composable {
            Image(imageVector = Icons.Filled.Clear, // 清除图标
                contentDescription = null,
                modifier = Modifier.clickable { text = "" }) // 给图标添加点击事件，点击就清空text
        },
    )
}