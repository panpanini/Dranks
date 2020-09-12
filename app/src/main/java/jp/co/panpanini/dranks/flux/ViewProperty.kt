package jp.co.panpanini.dranks.flux

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

class ViewProperty<T> private constructor(initialValue: T? = null){

    companion object {
        @JvmStatic
        fun <T> create() = ViewProperty<T>()

        @JvmStatic
        fun <T> create(initialValue: T?) = ViewProperty(initialValue)
    }

    private val _liveData = MutableLiveData(initialValue)

    private val mediator = MediatorLiveData<T>().apply {
        addSource(_liveData) { it?.let(::setValue) }
    }

    var value: T?
        get() = _liveData.value
        set(value) = _liveData.postValue(value)

    val liveData: LiveData<T> = mediator
}