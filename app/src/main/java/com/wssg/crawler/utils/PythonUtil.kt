package com.wssg.crawler.utils

import com.chaquo.python.PyObject
import com.chaquo.python.Python

/**
 * ...
 * @author RQ527 (Ran Sixiang)
 * @email 1799796122@qq.com
 * @date 2023/11/2
 * @Description:
 */
/**
 * 运行py代码字符串
 */
fun runByPyCode(code: String): PyObject =
    Python.getInstance().getModule("run_py").callAttr("getByPyScript", code)