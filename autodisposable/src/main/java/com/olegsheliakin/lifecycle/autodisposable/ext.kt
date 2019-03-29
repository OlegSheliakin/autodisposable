package com.olegsheliakin.lifecycle.autodisposable

import androidx.lifecycle.Lifecycle
import io.reactivex.disposables.Disposable

fun Disposable.disposeOnDestroy(autoDisposable: AutoDisposable) {
    autoDisposable.add(Lifecycle.Event.ON_DESTROY, this)
}

fun Disposable.disposeOnPause(autoDisposable: AutoDisposable) {
    autoDisposable.add(Lifecycle.Event.ON_PAUSE, this)
}

fun Disposable.disposeOnStop(autoDisposable: AutoDisposable) {
    autoDisposable.add(Lifecycle.Event.ON_STOP, this)
}

fun Disposable.disposeOnAny(autoDisposable: AutoDisposable) {
    autoDisposable.add(Lifecycle.Event.ON_ANY, this)
}


