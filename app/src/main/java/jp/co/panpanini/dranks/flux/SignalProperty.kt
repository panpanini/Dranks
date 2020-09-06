package jp.co.panpanini.dranks.flux

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

class SignalProperty<T> private constructor() {

    companion object {
        @JvmStatic
        fun <T> create() = SignalProperty<T>()
    }

    private val _liveData = MutableLiveData<Event<T?>>()

    private val mediator = MediatorLiveData<T>().apply {
        addSource(Transformations.map(_liveData) {
            it?.getContentIfNotHandled()
        }) { it?.let(::setValue) }
    }

    fun set(value: T?) {
        _liveData.value = Event(value)
    }

    val liveData: LiveData<T>
        get() = mediator

    class Event<T>(private val value: T?) {
        private var hasBeenHandled = false

        /**
         * Returns the content and prevents its use again.
         */
        fun getContentIfNotHandled(): T? {
            return if (hasBeenHandled) {
                null
            } else {
                hasBeenHandled = true
                value
            }
        }
    }
}