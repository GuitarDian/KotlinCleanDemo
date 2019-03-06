package com.example.hjk.kotlindemo.presentation.rule

/**
 * Created by Dian on 2018/12/23
 */
interface LoginRule {
    interface V {
        fun gotoMainActivity()
    }

    interface P {
        fun login(account: String, password: String)
    }
}
