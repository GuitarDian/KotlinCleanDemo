package com.example.hjk.kotlindemo.data.source

import com.example.hjk.kotlindemo.data.entity.User
import io.reactivex.Observable

/**
 * Created by HJK on 2018/9/19.
 * 数据请求接口
 */

interface IRepository {
    fun getUser(loginAccount: String, loginPsw: String): Observable<User>
    fun saveUser(user: User)
}