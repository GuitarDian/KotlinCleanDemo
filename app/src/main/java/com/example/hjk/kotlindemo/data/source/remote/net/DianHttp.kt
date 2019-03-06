package com.example.hjk.kotlindemo.data.source.remote.net

import android.os.Environment

import com.example.hjk.kotlindemo.data.constant.NC
import com.example.hjk.kotlindemo.data.source.remote.net.converter.GsonConvertFactory

import java.io.File
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

import okhttp3.Cache
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 * Created by Dian on 2018/11/27.
 * retrofit基本设置
 */

class DianHttp {

    val api: IDianApi

    init {
        val retrofit2 = Retrofit.Builder()
                .baseUrl(NC.BASE_URL)
                .addConverterFactory(GsonConvertFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createClient())
                .build()
        api = retrofit2.create(IDianApi::class.java)
    }

    private fun createClient(): OkHttpClient {
        val sdcache = Environment.getDownloadCacheDirectory()
        val cacheSize = 10 * 1024 * 1024
        val builder = OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(Cache(sdcache.absoluteFile, cacheSize.toLong()))
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        builder.cookieJar(JavaNetCookieJar(cookieManager))
        return builder.build()
    }

    companion object {
        fun build(): DianHttp {
            return HttpHolder.INSTANCE
        }
    }

    object HttpHolder {
        var INSTANCE = DianHttp()
    }

}
