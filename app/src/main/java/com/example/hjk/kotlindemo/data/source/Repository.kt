package com.example.hjk.kotlindemo.data.source

import com.example.hjk.kotlindemo.data.entity.User
import com.example.hjk.kotlindemo.data.source.local.ILocalRequest
import com.example.hjk.kotlindemo.data.source.remote.IRemoteRequest
import com.example.hjk.kotlindemo.presentation.util.Preconditions
import io.reactivex.Observable

/**
 * Created by HJK on 2018/9/19.
 * 数据请求
 */

class Repository private constructor(local: ILocalRequest, remote: IRemoteRequest) : IRepository {


    private val localRequest: ILocalRequest
    private val remoteRequest: IRemoteRequest

    init {
        localRequest = Preconditions.checkNotNull(local, "local source is null")
        remoteRequest = Preconditions.checkNotNull(remote, "remote source is null")
    }

    companion object {
        private var INSTANCE: Repository? = null
        fun getInstance(local: ILocalRequest, remote: IRemoteRequest): Repository {
            if (INSTANCE == null)
                INSTANCE = Repository(local, remote)
            return INSTANCE as Repository
        }
    }

    override fun getUser(loginAccount: String, loginPsw: String): Observable<User> {
        return remoteRequest.getUser(loginAccount, loginPsw)
    }

    override fun saveUser(user: User) {
        localRequest.saveUser(user)
    }
}
