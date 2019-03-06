package com.example.hjk.kotlindemo.data.source.remote


import com.example.hjk.kotlindemo.data.entity.User

import io.reactivex.Observable

/**
 * Created by Dian on 2018/12/23
 * 网络请求接口
 */
interface IRemoteRequest {
    fun getUser(loginAccount: String, loginPsw: String): Observable<User>
}
