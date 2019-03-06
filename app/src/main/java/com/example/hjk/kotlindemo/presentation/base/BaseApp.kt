package com.example.hjk.kotlindemo.presentation.base

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.os.Bundle

import com.example.hjk.kotlindemo.presentation.util.L

/**
 * Created by HJK on 2018/9/19.
 */

abstract class BaseApp : Application(), ActivityLifecycleCallbacks {

    companion object {
        lateinit var instance: BaseApp
            private set

        fun getContext() = instance?.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
        instance = this
        init()
    }

    protected abstract fun init()


    override fun onActivityCreated(activity: Activity, bundle: Bundle) {
        L.v(activity.localClassName)
    }

    override fun onActivityStarted(activity: Activity) {
        L.v(activity.localClassName)
    }

    override fun onActivityResumed(activity: Activity) {
        L.v(activity.localClassName)
    }

    override fun onActivityPaused(activity: Activity) {
        L.v(activity.localClassName)
    }

    override fun onActivityStopped(activity: Activity) {
        L.v(activity.localClassName)
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
        L.v(activity.localClassName)
    }

    override fun onActivityDestroyed(activity: Activity) {
        L.v(activity.localClassName)
    }

}
