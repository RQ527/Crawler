package com.wssg.crawler

import android.app.Application
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

/**
 * ...
 * @author RQ527 (Ran Sixiang)
 * @email 1799796122@qq.com
 * @date 2023/10/24
 * @Description:
 */
class App:Application() {
    companion object{
        lateinit var mContext:App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        mContext=this
        if (!Python.isStarted()){
            Python.start(AndroidPlatform(mContext))
        }
    }
}