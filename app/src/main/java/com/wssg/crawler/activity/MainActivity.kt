package com.wssg.crawler.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wssg.crawler.page.HomePage
import com.wssg.crawler.page.SearchCourse
import com.wssg.crawler.ui.theme.CrawlerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrawlerTheme {
                val naviController = rememberNavController()
                NavHost(
                    navController = naviController,
                    startDestination = RouteConfig.HOMEPAGE,
                    enterTransition = { slideInHorizontally { it } },
                    exitTransition = { slideOutHorizontally { it } },
                ) {
                    composable(RouteConfig.HOMEPAGE) {
                        HomePage {
                            naviController.navigate(it)
                        }
                    }
                    composable(
                        "${RouteConfig.EDIT_PAGE}/{${RouteConfig.IS_PY_PAGE_PARAM}}",
                        arguments = listOf(navArgument(RouteConfig.IS_PY_PAGE_PARAM) {
                            type = NavType.BoolType
                        })
                    ) {
                        SearchCourse(isPyPage = requireNotNull(it.arguments).getBoolean(RouteConfig.IS_PY_PAGE_PARAM)) { isPyPage, content ->
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    TextActivity::class.java
                                ).apply {
                                    putExtra("key", content)
                                    putExtra("isPyPage", isPyPage)
                                })
                        }
                    }
                }
            }
        }
    }
}

object RouteConfig {
    const val HOMEPAGE = "home/homepage"
    const val EDIT_PAGE = "editPage"
    const val IS_PY_PAGE_PARAM = "isPyPage"
}