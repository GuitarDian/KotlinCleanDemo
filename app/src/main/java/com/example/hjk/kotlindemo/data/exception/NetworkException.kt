package com.example.hjk.kotlindemo.data.exception

/**
 * Created by Dian on 2017/10/24.
 * 网络异常
 */
class NetworkException : Exception {
    constructor() {}
    constructor(message: String) : super(message) {}
}
