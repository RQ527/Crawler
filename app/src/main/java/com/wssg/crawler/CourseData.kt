package com.wssg.crawler

data class ApiWrapper<T:Any>(
    val code: Int,
    val data: T,
    val message: String
)