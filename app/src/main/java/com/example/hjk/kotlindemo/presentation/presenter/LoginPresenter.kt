package com.example.hjk.kotlindemo.presentation.presenter


import com.example.hjk.kotlindemo.data.entity.User
import com.example.hjk.kotlindemo.domain.usercase.LoginUseCase
import com.example.hjk.kotlindemo.presentation.base.BasePresenter
import com.example.hjk.kotlindemo.presentation.rule.LoginRule
import com.example.hjk.kotlindemo.presentation.util.Preconditions
import io.reactivex.observers.DisposableObserver

/**
 * Created by Dian on 2018/12/23
 */
class LoginPresenter(loginUseCase: LoginUseCase) : BasePresenter<LoginRule.V>(), LoginRule.P {

    private var loginUseCase : LoginUseCase = Preconditions.checkNotNull(loginUseCase, "loginUseCase is null")

    override fun detach() {
        loginUseCase.dispose()
    }

    override fun login(account: String, password: String) {
        loginUseCase.execute(LoginObserver(), LoginUseCase.RequestValue(account, password))
    }

    private inner class LoginObserver : DisposableObserver<User>() {

        override fun onNext(user: User) {
            loginUseCase.saveUser(user)
        }

        override fun onError(e: Throwable) {}

        override fun onComplete() {}
    }

}
