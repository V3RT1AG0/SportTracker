package com.example.sporttracker.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.sporttracker.R
import com.example.sporttracker.viewmodel.SportViewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sporttracker.model.Event
import com.example.sporttracker.view.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*


class SportsMain : AppCompatActivity() {
    lateinit var sportViewModel: SportViewModel
    private val mylist = mutableListOf<Event>()
    private val eventAdapter = EventAdapter(mylist)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sportViewModel = ViewModelProviders.of(this).get(SportViewModel::class.java)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = eventAdapter
        }

        setUpSearchFragment()
        setObservers()
        sportViewModel.fetchEventHistoryfor(133612)
    }

    private fun setUpSearchFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val dialogFragment = SearchFragment()
        dialogFragment.show(fragmentTransaction, "dialog")
    }

    private fun setObservers(){
        sportViewModel.events.observe(this, Observer { events ->
            events?.let {
                eventAdapter.refresh(it)
                recyclerView.visibility = View.VISIBLE
            }
        })

        sportViewModel.loading.observe(this, Observer { loading ->
            loading?.let { progressBar.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    recyclerView.visibility = View.GONE
                    error_view.visibility = View.GONE
                }}
        })

        sportViewModel.error.observe(this, Observer { errorOccoured ->
            errorOccoured?.let {
                error_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    recyclerView.visibility = View.GONE
                    error_view.visibility = View.GONE
                }
            }
        })
    }
}
