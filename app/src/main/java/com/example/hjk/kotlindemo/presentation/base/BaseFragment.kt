package com.example.hjk.kotlindemo.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import butterknife.ButterKnife
import butterknife.Unbinder

/**
 * Created by HJK on 2018/9/19.
 */

abstract class BaseFragment : Fragment() {

    private lateinit var unbinder: Unbinder
    private var basePresenter: BasePresenter<*>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflate(inflater, container)
        unbinder = ButterKnife.bind(this, rootView)
        basePresenter = genPresenter()
        basePresenter?.let { it.attachView(this) }
        initData(savedInstanceState)
        return rootView
    }

    override fun onDestroyView() {
        basePresenter?.let {
            it.detach()
            it.detachView()
            basePresenter = null
        }
        unbinder.unbind()
        super.onDestroyView()
    }

    abstract fun inflate(inflater: LayoutInflater, container: ViewGroup?): View

    abstract fun initData(savedInstanceState: Bundle?)

    protected abstract fun genPresenter(): BasePresenter<*>
}
