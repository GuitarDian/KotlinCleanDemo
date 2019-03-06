package com.example.hjk.kotlindemo.data.source.remote.net.request

import com.example.hjk.kotlindemo.data.source.remote.net.body.Request
import com.example.hjk.kotlindemo.presentation.util.L
import com.google.gson.JsonObject

import okhttp3.RequestBody

/**
 * Created by Dian on 2018/11/27.
 */

class LoginRequest(private val account: String, private val password: String) : Request() {

    override fun getRequestBody(): RequestBody {
        val bo = "\$data=" + getRequestUrl()
        L.d(bo)
        return RequestBody.create(FORM_CONTENT_TYPE, bo)
    }

    override fun createRequestBody(): JsonObject {
        val jo = JsonObject()
        jo.addProperty("account", account)
        jo.addProperty("password", password)
        return jo
    }
}
