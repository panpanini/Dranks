package jp.co.panpanini.dranks.flux

import androidx.lifecycle.LiveData

class SignalProperty<T> private constructor() {

    companion object {
        @JvmStatic
        fun <T> create() = SignalProperty<T>()
    }

    private val _liveEvent = LiveEvent<T>()

    fun set(value: T?) {
        // only emit if someone is listening
        if (_liveEvent.hasActiveObservers()) {
            value?.let(_liveEvent::postValue)
        }
    }

    val liveData: LiveData<T>
        get() = _liveEvent
}