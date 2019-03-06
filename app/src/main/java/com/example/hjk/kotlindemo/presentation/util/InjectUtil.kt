package com.example.hjk.kotlindemo.presentation.util


import android.content.Context
import com.example.hjk.kotlindemo.data.source.Repository
import com.example.hjk.kotlindemo.data.source.local.LocalSource
import com.example.hjk.kotlindemo.data.source.remote.RemoteSource
import com.example.hjk.kotlindemo.domain.usercase.LoginUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Dian on 2018/11/26.
 * 初始化UseCase
 */

object InjectUtil {

    fun provideDianRepository(context: Context): Repository {
        Preconditions.checkNotNull(context)
        return Repository.getInstance(LocalSource.getInstance(),
                RemoteSource.getInstance())
    }

    fun provideLoginUseCase(context: Context): LoginUseCase {
        Preconditions.checkNotNull(context)
        return LoginUseCase(Schedulers.io(), AndroidSchedulers.mainThread(), provideDianRepository(context))
    }


}
