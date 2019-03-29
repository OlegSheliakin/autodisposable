package com.olegsheliakin.lifecycle.autodisposable

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.EnumMap

class AutoDisposable private constructor(
    lifecycleOwner: LifecycleOwner
) : LifecycleObserver {

    private val map: MutableMap<Lifecycle.Event, CompositeDisposable> by lazy {
        return@lazy EnumMap<Lifecycle.Event, CompositeDisposable>(Lifecycle.Event::class.java)
    }

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    companion object {
        fun create(lifecycleOwner: LifecycleOwner): AutoDisposable {
            return AutoDisposable(lifecycleOwner)
        }
    }

    fun add(onEvent: Lifecycle.Event, disposable: Disposable) {
        map.getOrPut(onEvent) { CompositeDisposable() }.add(disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny(lifecycleOwner: LifecycleOwner, event: Lifecycle.Event) {
        map[event]?.clear()
    }

}