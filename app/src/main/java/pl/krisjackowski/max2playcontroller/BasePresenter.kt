package pl.krisjackowski.max2playcontroller

import android.support.annotation.CallSuper
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<T : MvpView> : MvpBasePresenter<T>() {
    val viewDisposables: CompositeDisposable = CompositeDisposable()

    override fun detachView() {
        viewDisposables.dispose()
        super.detachView()
    }
}