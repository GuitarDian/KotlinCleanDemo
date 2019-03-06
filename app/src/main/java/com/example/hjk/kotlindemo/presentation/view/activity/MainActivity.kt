package com.example.hjk.kotlindemo.presentation.view.activity

import com.example.hjk.kotlindemo.presentation.base.BaseActivity
import com.example.hjk.kotlindemo.presentation.base.BasePresenter
import com.example.hjk.mykotlindemo.R


class MainActivity : BaseActivity() {

    override fun setContentView() {
        setContentView(R.layout.activity_main)
    }

    override fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun genPresenter(): BasePresenter<*>? {
       return null
    }


}
