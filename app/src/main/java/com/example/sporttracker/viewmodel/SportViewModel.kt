package com.example.sporttracker.viewmodel


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.showtracker.model.ApiService
import com.example.sporttracker.model.Event
import com.example.sporttracker.model.History
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SportViewModel(application: Application) : AndroidViewModel(application) {
    val events = MutableLiveData<List<Event>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    val disposable = CompositeDisposable()

    init {
        loading.value = true
        error.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun fetchEventHistoryfor(id:Int) {
        loading.value = true
        fetchHistory(id)
    }

    private fun fetchHistory(id:Int) {
        disposable.add(ApiService.getSportsApi().getHistory(id)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object:DisposableSingleObserver<History>(){
                override fun onSuccess(data: History) {
                    loading.value = false
                    error.value = false
                    events.value = data.events
                }

                override fun onError(e: Throwable) {
                    Log.e("error",e.message)
                    loading.value = false
                    error.value = true
                }
            }))


    }
}
