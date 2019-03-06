package com.example.hjk.kotlindemo.data.source.remote.net


import com.example.hjk.kotlindemo.data.entity.User

import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Dian on 2018/12/24
 * retrofit接口
 */
interface IDianApi {

    @POST("service")
    fun getUser(@Body requestBody: RequestBody): Observable<User>
}
