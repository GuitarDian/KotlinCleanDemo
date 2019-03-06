package com.example.hjk.kotlindemo.data.source.remote.net.body

import com.google.gson.JsonObject

import okhttp3.MediaType

/**
 * Created by Dian on 2018/11/27.
 * 统一请求接口
 */

abstract class Request : IRequest {

    companion object {
        val FORM_CONTENT_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8")
    }

    override fun getRequestUrl(): String {
        val serviceContent = createRequestBody()
        val jsonObj = JsonObject()
        jsonObj.add("serviceContent", serviceContent)
        return jsonObj.toString()
    }

    abstract fun createRequestBody(): JsonObject

}