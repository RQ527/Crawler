package com.wssg.crawler

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.google.gson.Gson
import com.wssg.crawler.utils.runByPyCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ...
 * @author RQ527 (Ran Sixiang)
 * @email 1799796122@qq.com
 * @date 2023/10/24
 * @Description:
 */
class MainViewModel : ViewModel() {
    val inputResFlow: StateFlow<String>
        get() = _inputResFlow
    private var _inputResFlow = MutableStateFlow("")
    fun getCourse(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getDataFromPythonScript<String> {
                Python.getInstance().getModule("search_course_by_id").callAttr("get", id)
            }.stateIn(viewModelScope).collect(_inputResFlow)
        }
    }

    fun runPyScript(code:String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.e("RQ527", "runPyScript: $code", )
                _inputResFlow.value = runByPyCode(code).toString()
            }catch (e:Throwable){
                object :Handler(Looper.getMainLooper()){
                    override fun handleMessage(msg: Message) {
                        super.handleMessage(msg)
                        Toast.makeText(App.mContext, "python代码有误~", Toast.LENGTH_SHORT).show()
                    }
                }.sendEmptyMessage(0)
                e.printStackTrace()
            }
        }
    }

    private fun <T : Any> getDataFromPythonScript(pythonFun: () -> PyObject): Flow<T> {
        return flow {
            val callAttr = pythonFun()
            val res = Gson().fromJson(callAttr.toString(), ApiWrapper::class.java)
            if (res.code == 403) {
                Toast.makeText(App.mContext, "请连接校园网访问", Toast.LENGTH_SHORT).show()
            } else if (res.code == 200) {
                res.data as T
                emit(res.data)
            } else {
                Toast.makeText(App.mContext, "服务异常~", Toast.LENGTH_SHORT).show()
            }
        }.catch {
            it.printStackTrace()
            Toast.makeText(App.mContext, "网络异常~", Toast.LENGTH_SHORT).show()
        }
    }
}