package com.example.hjk.kotlindemo.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import butterknife.ButterKnife
import butterknife.Unbinder

/**
 * Created by HJK on 2018/9/19.
 */

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var unbinder: Unbinder
    private var basePresenter: BasePresenter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
        unbinder = ButterKnife.bind(this)
        basePresenter = genPresenter()
        basePresenter?.let { it.attachView(this)  }
        initData()
    }

    override fun onDestroy() {
        basePresenter?.let {
            it.detach()
            it.detachView()
            basePresenter = null
        }
        unbinder.unbind()
        super.onDestroy()
    }

    protected abstract fun setContentView()

    protected abstract fun initData()

    protected abstract fun genPresenter(): BasePresenter<*>?
}