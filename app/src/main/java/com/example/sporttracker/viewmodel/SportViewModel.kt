package com.example.sporttracker.viewmodel


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sporttracker.model.ApiService
import com.example.sporttracker.model.Event
import com.example.sporttracker.model.History
import com.example.sporttracker.model.Team
import com.example.sporttracker.model.Teams
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class SportViewModel(application: Application) : AndroidViewModel(application) {
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    val disposable = CompositeDisposable()
    val teamDetails = MutableLiveData<TeamDetails>()

    init {
        loading.value = true
        error.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun fetchEventHistoryfor(id: Int) {
        loading.value = true
        fetchHistory(id)
    }

    data class TeamDetails(
        var team:Team,
        var events:List<Event>
    )


    private fun fetchHistory(id: Int) {
        disposable.add(
            Single.zip(
                ApiService.getSportsApi().getHistory(id),
                ApiService.getSportsApi().getTeamDetails(id),
                BiFunction<History,Teams, TeamDetails> { history,teams->
                    TeamDetails(teams.teams[0], history.events ?: emptyList())
                }
            ).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<TeamDetails>() {
                    override fun onSuccess(t: TeamDetails) {
                        loading.value = false
                        teamDetails.value = t
                        Log.d("Hello",Thread.currentThread().name)
                    }

                    override fun onError(e: Throwable) {
                        error.value = true
                        Log.e("Error",e.message)
                    }

                }))

    }
}
