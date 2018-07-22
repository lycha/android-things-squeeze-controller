package pl.krisjackowski.max2playcontroller

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit

class MainViewPresenter : BasePresenter<MainViewContract.View>(), MainViewContract.Presenter {

    private val dimLightEmitter = Observable.interval(10, TimeUnit.SECONDS)
            .startWith(0L)  // For an immediate refresh
            .observeOn(AndroidSchedulers.mainThread())

    override fun onResume() {
        viewDisposables.addAll(
                subscribeToWindowTouches(),
                startDimLightInterval()
        )
    }

    private fun startDimLightInterval() = dimLightEmitter
            .timeInterval()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { view.setBrightness(BRIGHTNESS_NONE) }

    private fun subscribeToWindowTouches() = view.onWindowTouched()
            .subscribeBy(
                onNext = {
                    ifViewAttached{it.setBrightness(BRIGHTNESS_MAX)}
                    dimLightEmitter.startWith(0L)
                },
                onError = { Log.d("MainViewPresenter", it.message) }
        )

    companion object {
        val BRIGHTNESS_MAX = 1f
        val BRIGHTNESS_NONE = 0f
    }
}