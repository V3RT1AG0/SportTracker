package com.example.sporttracker.view.search



import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sporttracker.R
import com.example.sporttracker.model.Team
import com.example.sporttracker.viewmodel.SearchFragementViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.sporttracker.view.events.EventsActivity
import kotlinx.android.synthetic.main.fragment_search.*
import android.os.Handler

import android.widget.EditText





class SearchFragment : DialogFragment() {
    //    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val builder = AlertDialog.Builder(activity)
//        builder.setPositiveButton("Cool") { dialog, which -> dismiss() }
//        builder.setNegativeButton("Cancel") { dialog, which -> dismiss() }
//        return builder.create()
//    }
    lateinit var searchViewModel: SearchFragementViewModel
    private val teamsList = mutableListOf<Team>()
    lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_search, container, false)
        val editText = v.findViewById(R.id.search_bar) as EditText
        editText.requestFocus()
        dialog!!.getWindow()!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Material_Light_Dialog)
    }

    override fun onResume() {
        super.onResume()
        Handler().post(Runnable {  val width = resources.getDimensionPixelSize(R.dimen.frag_width)
            val height = resources.getDimensionPixelSize(R.dimen.frag_height)
            dialog!!.window!!.setLayout(width, height) })

    }



//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val dialog = super.onCreateDialog(savedInstanceState)
//        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
//        return dialog;
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchAdapter = SearchAdapter(teamsList,activity as EventsActivity)
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