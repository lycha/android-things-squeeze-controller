package pl.krisjackowski.max2playcontroller

import android.view.MotionEvent
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

class MainViewContract {
    interface View : MvpView {
        fun onWindowTouched(): Observable<MotionEvent>
        fun setBrightness(brightness: Float)
    }

    interface Presenter : MvpPresenter<View> {
        fun onResume()
    }
}