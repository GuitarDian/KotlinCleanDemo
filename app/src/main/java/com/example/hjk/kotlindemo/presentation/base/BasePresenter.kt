package com.example.hjk.kotlindemo.presentation.base

import java.lang.ref.WeakReference

/**
 * Created by HJK on 2018/9/19.
 */

abstract class BasePresenter<T> {

    var viewReference: WeakReference<*>? = null

    fun <T> attachView(view: T){
        viewReference = WeakReference(view)
    }

    fun detachView() {
        viewReference?.let {
            it.clear()
            viewReference = null
        }

    }

    fun isViewAttached(): Boolean {
        return viewReference != null && viewReference?.get() != null
    }

    fun getView(): T? {
        return if (!isViewAttached()) null else viewReference?.get() as T
    }

    abstract fun detach()
}
