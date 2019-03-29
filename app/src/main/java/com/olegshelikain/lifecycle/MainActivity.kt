package com.olegshelikain.lifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.olegsheliakin.lifecycle.autodisposable.AutoDisposable
import com.olegsheliakin.lifecycle.autodisposable.disposeOnDestroy
import com.olegsheliakin.lifecycle.autodisposable.disposeOnPause
import com.olegsheliakin.lifecycle.autodisposable.disposeOnStop
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.name

    private val autoDisposable = AutoDisposable.create(this as LifecycleOwner)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Observable.interval(1, TimeUnit.SECONDS)
            .subscribe { Log.d(TAG, "Next value: $it") }
            .disposeOnPause(autoDisposable)

        Observable.interval(1, TimeUnit.SECONDS)
            .subscribe { Log.d(TAG, "Next value: $it") }
            .disposeOnDestroy(autoDisposable)

        Observable.interval(1, TimeUnit.SECONDS)
            .subscribe { Log.d(TAG, "Next value: $it") }
            .disposeOnStop(autoDisposable)

        btnShowDialog.setOnClickListener {
            //Opens Activity as a Dialog to emulate onPause
            val intent = Intent(this, DialogActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
    }
}
