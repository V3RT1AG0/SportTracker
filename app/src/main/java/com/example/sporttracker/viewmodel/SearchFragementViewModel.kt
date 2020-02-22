package com.example.sporttracker.viewmodel


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.showtracker.model.ApiService
import com.example.sporttracker.model.Event
import com.example.sporttracker.model.History
import com.example.sporttracker.model.Team
import com.example.sporttracker.model.Teams
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SearchFragementViewModel(application: Application) : AndroidViewModel(application) {
    val teams = MutableLiveData<List<Team>>()
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

    fun searchFor(query: String) {
        loading.value = true
        fetchTeams(query)
    }

    private fun fetchTeams(query:String) {
        disposable.add(ApiService.getSportsApi().getTeams(query)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object:DisposableSingleObserver<Teams>(){
                override fun onSuccess(data: Teams) {
                    Log.d("logger",data.toString())
                    loading.value = false
                    error.value = false
                    teams.value = data.teams
                }

                override fun onError(e: Throwable) {
                    Log.e("error",e.message)
                    loading.value = false
                    error.value = true
                }
            }))


    }
}
