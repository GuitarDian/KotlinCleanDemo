package com.example.hjk.kotlindemo.data.source.remote.net.body

import okhttp3.RequestBody

/**
 * Created by Dian on 2017/10/23.
 */
interface IRequest {
    fun getRequestUrl() : String
    fun getRequestBody() : RequestBody
}
