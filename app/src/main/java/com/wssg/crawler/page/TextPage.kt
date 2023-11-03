package com.wssg.crawler.page

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wssg.crawler.MainViewModel

/**
 * ...
 * @author RQ527 (Ran Sixiang)
 * @email 1799796122@qq.com
 * @date 2023/10/29
 * @Description:
 */
@Composable
fun TextPage(
    isPyPage: Boolean,
    key: String,
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val data = viewModel.inputResFlow.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
    if (isPyPage) viewModel.runPyScript(key) else viewModel.getCourse(key)
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(scrollState), text = buildAnnotatedString {
            append(data.value)
        }
    )

}