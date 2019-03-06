package com.example.hjk.kotlindemo.presentation.view.activity

import android.view.View
import android.widget.EditText

import butterknife.BindView
import butterknife.OnClick
import com.example.hjk.kotlindemo.presentation.base.BaseActivity
import com.example.hjk.kotlindemo.presentation.base.BaseApp
import com.example.hjk.kotlindemo.presentation.base.BasePresenter
import com.example.hjk.kotlindemo.presentation.presenter.LoginPresenter
import com.example.hjk.kotlindemo.presentation.rule.LoginRule
import com.example.hjk.kotlindemo.presentation.util.InjectUtil
import com.example.hjk.kotlindemo.presentation.util.Preconditions
import com.example.hjk.kotlindemo.presentation.util.TextUtil
import com.example.hjk.kotlindemo.presentation.util.ToastUtil
import com.example.hjk.mykotlindemo.R

/**
 * Created by Dian on 2018/12/2s3
 * 登录界面
 */
class LoginActivity : BaseActivity() {

    @BindView(R.id.et_account)
    lateinit var etAccount: EditText

    @BindView(R.id.et_password)
    lateinit var etPassword: EditText

    lateinit var presenter: LoginRule.P

    override fun setContentView() {
        setContentView(R.layout.activity_login)
    }

    override fun initData() {}

    override fun genPresenter() : BasePresenter<*>{
        presenter = LoginPresenter(InjectUtil.provideLoginUseCase(BaseApp.getContext()))
        return presenter as LoginPresenter
    }

    @OnClick(R.id.btn_login)
    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login -> {
                val account = etAccount.text.toString()
                val password = etPassword.text.toString()
                if (TextUtil.isEmpty(account) || TextUtil.isEmpty(password)) {
                    ToastUtil.showShort(BaseApp.getContext(), "请填写完整的信息")
                    return
                }
                Preconditions.checkNotNull(presenter).login(account, password)
            }
        }
    }
}
