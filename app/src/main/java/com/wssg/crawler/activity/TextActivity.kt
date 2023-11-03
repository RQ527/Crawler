package com.wssg.crawler.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.wssg.crawler.page.TextPage
import com.wssg.crawler.ui.theme.CrawlerTheme

/**
 * ...
 * @author RQ527 (Ran Sixiang)
 * @email 1799796122@qq.com
 * @date 2023/11/3
 * @Description:
 */
class TextActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val key = intent.getStringExtra("key")!!
        val isPyPage = intent.getBooleanExtra("isPyPage",false)
        setContent {
            CrawlerTheme {
                TextPage(isPyPage = isPyPage, key = key)
            }
        }
    }
}