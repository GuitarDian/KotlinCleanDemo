package com.example.hjk.kotlindemo.data.source.local

import com.example.hjk.kotlindemo.data.entity.User


/**
 * Created by HJK on 2018/9/19.
 */

class LocalSource private constructor() : ILocalRequest {

    companion object {
        fun getInstance() = Holder.instance
    }

    private object Holder{
        val instance = LocalSource()
    }

    override fun saveUser(user: User) {

    }

}
