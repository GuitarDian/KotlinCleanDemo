package com.example.hjk.kotlindemo.data.source.local

import com.example.hjk.kotlindemo.data.entity.User

/**
 * Created by HJK on 2018/9/19.
 * 本地数据接口
 */

interface ILocalRequest {
    fun saveUser(user: User)
}
