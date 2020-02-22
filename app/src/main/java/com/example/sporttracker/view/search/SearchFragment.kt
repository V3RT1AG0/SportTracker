package com.example.sporttracker.view.search


import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sporttracker.R
import com.example.sporttracker.model.Event
import com.example.sporttracker.model.Team
import com.example.sporttracker.view.EventAdapter
import com.example.sporttracker.viewmodel.SearchFragementViewModel
import com.example.sporttracker.viewmodel.SportViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.sporttracker.view.SportsMain
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : DialogFragment() {
    //    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val builder = AlertDialog.Builder(activity)
//        builder.setPositiveButton("Cool") { dialog, which -> dismiss() }
//        builder.setNegativeButton("Cancel") { dialog, which -> dismiss() }
//        return builder.create()
//    }
    lateinit var searchViewModel: SearchFragementViewModel
    private val teamsList = mutableListOf<Team>()
    private val searchAdapter = SearchAdapter(teamsList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_Light_NoTitleBar_Fullscreen)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel = ViewModelProviders.of(this).get(SearchFragementViewModel::class.java)
        searchrecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }

        search_bar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                searchViewModel.searchFor(editable.toString())
                searchViewModel.teams.observe(this@SearchFragment, Observer { teams ->
                    teams?.let {
                        searchAdapter.refresh(it)
                        searchrecyclerView.visibility = View.VISIBLE
                    }
                })
            }

        })
    }


}