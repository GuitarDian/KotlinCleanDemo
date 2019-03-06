package com.example.hjk.kotlindemo.domain.usercase


import com.example.hjk.kotlindemo.data.entity.User
import com.example.hjk.kotlindemo.data.source.Repository
import com.example.hjk.kotlindemo.domain.BaseUseCase
import com.example.hjk.kotlindemo.presentation.util.Preconditions
import io.reactivex.Observable
import io.reactivex.Scheduler

/**
 * Created by Dian on 2018/12/25
 */
class LoginUseCase(observerThread: Scheduler,
                   subcriberThread: Scheduler,
                   private val repository: Repository)
    : BaseUseCase<LoginUseCase.RequestValue, User>(observerThread, subcriberThread) {

    override fun buildUseCaseObservable(request: RequestValue): Observable<User> {
        return repository.getUser(request.account, request.password)
    }


    class RequestValue(account: String, password: String) {
        internal val account: String
        internal val password: String

        init {
            this.account = Preconditions.checkNotNull(account)
            this.password = Preconditions.checkNotNull(password)
        }
    }

    fun saveUser(user: User) {
        repository.saveUser(user)
    }
}
