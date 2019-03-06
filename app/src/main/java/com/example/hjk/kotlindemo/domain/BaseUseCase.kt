package com.example.hjk.kotlindemo.domain


import com.example.hjk.kotlindemo.presentation.util.Preconditions
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

/**
 * Created by Dian on 2018/12/23
 * UseCase基类
 */
abstract class BaseUseCase<P, Q>(private val observerThread: Scheduler,
                                 private val subcriberThread: Scheduler) {

    private val disposables: CompositeDisposable

    init {
        this.disposables = CompositeDisposable()
    }

    protected abstract fun buildUseCaseObservable(request: P): Observable<Q>

    fun execute(observer: DisposableObserver<Q>, request: P) {
        Preconditions.checkNotNull(observer)
        val observable = this.buildUseCaseObservable(request)
                .subscribeOn(observerThread)
                .observeOn(subcriberThread)
        addDisposable(observable.subscribeWith(observer))
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun addDisposable(disposable: Disposable) {
        Preconditions.checkNotNull(disposable)
        Preconditions.checkNotNull(disposables)
        disposables.add(disposable)
    }

}
