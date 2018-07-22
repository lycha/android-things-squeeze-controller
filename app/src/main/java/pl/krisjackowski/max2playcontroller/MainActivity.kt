package pl.krisjackowski.max2playcontroller

import android.app.Activity
import android.os.Bundle
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.jakewharton.rxbinding2.view.touches

class MainActivity : MvpActivity<MainViewContract.View, MainViewContract.Presenter>(), MainViewContract.View {
    override fun setBrightness(brightness: Float) {
        val params = window.attributes
        params.screenBrightness = brightness
        window.attributes = params
    }

    override fun createPresenter() = MainViewPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBrightness(0f)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onWindowTouched() = windowContainer.touches()
}

