package com.example.hjk.kotlindemo.presentation.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by XTER on 2017/10/12.
 * 网络相关工具
 */
object NetUtil {

    @SuppressLint("MissingPermission")
            /**
     * 网络可用？
     *
     * @return bool
     */
    fun isInternetConnection(context: Context): Boolean {
        val isConnected: Boolean
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting

        return isConnected
    }
}
