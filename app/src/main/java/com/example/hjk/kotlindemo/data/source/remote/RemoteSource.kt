package com.example.hjk.kotlindemo.data.source.remote


import com.example.hjk.kotlindemo.data.entity.User
import com.example.hjk.kotlindemo.data.source.Repository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Dian on 2018/12/23
 * 网络数据请求
 */
class RemoteSource private constructor() : IRemoteRequest {

    private val compositeDisposable: CompositeDisposable

    init {
        compositeDisposable = CompositeDisposable()
    }

    companion object {
        fun getInstance() = Holder.instance
    }

    private object Holder{
        val instance = RemoteSource()
    }


    override fun getUser(loginAccount: String, loginPsw: String): Observable<User> {
//        return Observable.create(ObservableOnSubscribe<Any> { e ->
//            if (SysUtil.isInternetConnection()) {
//                compositeDisposable.add(DianHttp.build().getApi().getUser(
//                        LoginRequest(loginAccount, loginPsw).getRequestBody())
//                        .subscribe(Consumer<Any> {
//                            //TODO 做你的操作
//                        }, Consumer<Throwable> { throwable -> e.onError(throwable) }))
//            } else {
//                e.onError(NetworkException("网络不可用"))
//            }
//        })
        TODO("not implemented")

    }

}
